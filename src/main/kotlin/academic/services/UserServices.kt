package academic.services

import academic.models.User
import academic.repositories.UserRepository
import academic.dtos.LoginRequest
import academic.dtos.RegisterRequest
import academic.security.JwtConfig
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import java.util.*

class UserService {
    private val userRepo = UserRepository()

    fun getAll(): List<User> = userRepo.getAll()

    fun getById(id: UUID): User? = userRepo.findById(id)

    fun getByEmail(email: String): User? = userRepo.findByEmail(email)

    fun create(user: User): User = userRepo.create(user)

    fun delete(id: UUID): Boolean = userRepo.delete(id)

    fun register(request: RegisterRequest): User {
        val user = User(
            id = UUID.randomUUID(),
            email = request.email,
            password = request.password, // Should hash in production
            name = request.name,
            role = request.role.name
        )
        return userRepo.create(user)
    }

    fun login(request: LoginRequest): String {
        val user = userRepo.findByEmail(request.email)
            ?: throw Exception("User not found")
        
        if (user.password != request.password) { // Should verify hash in production
            throw Exception("Invalid password")
        }
        
        return JwtConfig.generateToken(user.id.toString(), user.role)
    }

    suspend fun getCurrentUser(call: ApplicationCall): User {
        val principal = call.principal<JWTPrincipal>()
            ?: throw Exception("No authentication")
        
        val userId = principal.payload.getClaim("userId").asString()
        return getById(UUID.fromString(userId))
            ?: throw Exception("User not found")
    }
}
