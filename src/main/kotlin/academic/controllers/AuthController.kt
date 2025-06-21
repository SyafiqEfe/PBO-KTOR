package academic.controllers

import academic.repositories.UserRepository
import academic.dtos.LoginRequest
import academic.dtos.RegisterRequest
import academic.exceptions.AcademicException
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.auth.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

class AuthController(private val userRepository: UserRepository) {
    companion object {
        private const val SECRET = "your-256-bit-secret" // Should be in config in production
        private const val ISSUER = "academic-system"
        private const val VALIDITY_MS = 86_400_000 // 24 hours
        
        val JWT_VERIFIER = JWT.require(Algorithm.HMAC256(SECRET))
            .withIssuer(ISSUER)
            .build()
    }

    suspend fun login(call: ApplicationCall) {
        val request = call.receive<LoginRequest>()
        val user = userRepository.findByEmail(request.email) 
            ?: throw AcademicException("Invalid credentials")
        
        if (!user.validatePassword(request.password)) {
            throw AcademicException("Invalid credentials")
        }

        val token = generateToken(user.id, user.role.name)
        call.respond(mapOf("token" to token))
    }

    suspend fun register(call: ApplicationCall) {
        val request = call.receive<RegisterRequest>()
        
        if (userRepository.findByEmail(request.email) != null) {
            throw AcademicException("Email already registered")
        }

        val user = userRepository.create(
            email = request.email,
            password = request.password,
            role = request.role,
            name = request.name
        )

        call.respond(mapOf(
            "message" to "Registration successful",
            "userId" to user.id,
            "token" to generateToken(user.id, user.role.name) // Optional: auto-login after register
        ))
    }

    suspend fun getMe(call: ApplicationCall) {
        val principal = call.principal<JWTPrincipal>() 
            ?: throw AcademicException("Unauthorized")
        
        val userId = principal.payload.getClaim("id").asString()
            ?: throw AcademicException("Invalid token")
        
        val user = userRepository.findById(userId) 
            ?: throw AcademicException("User not found")

        call.respond(mapOf(
            "id" to user.id,
            "name" to user.name,
            "email" to user.email,
            "role" to user.role
        ))
    }

    private fun generateToken(userId: String, role: String): String {
        return JWT.create()
            .withIssuer(ISSUER)
            .withClaim("id", userId)
            .withClaim("role", role)
            .withExpiresAt(Date(System.currentTimeMillis() + VALIDITY_MS))
            .sign(Algorithm.HMAC256(SECRET))
    }
}
