package academic.controllers

import academic.dtos.LoginRequest
import academic.dtos.RegisterRequest
import academic.services.UserService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: String,
    val name: String,
    val email: String,
    val role: String
)

@Serializable
data class RegisterResponse(
    val message: String,
    val user: UserResponse
)

class AuthController(private val userService: UserService) {
    
    suspend fun register(call: ApplicationCall) {
        try {
            val request = call.receive<RegisterRequest>()
            
            // Basic validation
            if (request.email.isBlank()) {
                call.respond(HttpStatusCode.BadRequest, "Email is required")
                return
            }
            
            if (request.password.length < 6) {
                call.respond(HttpStatusCode.BadRequest, "Password must be at least 6 characters")
                return
            }
            
            if (request.name.isBlank()) {
                call.respond(HttpStatusCode.BadRequest, "Name is required")
                return
            }
            
            // Check if user already exists
            val existingUser = userService.getByEmail(request.email)
            if (existingUser != null) {
                call.respond(HttpStatusCode.BadRequest, "Email already registered")
                return
            }
            
            val user = userService.register(request)
            val response = RegisterResponse(
                message = "Registration successful",
                user = UserResponse(
                    id = user.id.toString(),
                    name = user.name,
                    email = user.email,
                    role = user.role
                )
            )
            call.respond(HttpStatusCode.Created, response)
            
        } catch (e: Exception) {
            call.application.log.error("Registration error", e)
            call.respond(HttpStatusCode.InternalServerError, "Registration failed: ${e.message}")
        }
    }

    suspend fun login(call: ApplicationCall) {
        try {
            val request = call.receive<LoginRequest>()
            val token = userService.login(request)
            call.respond(mapOf("token" to token))
        } catch (e: Exception) {
            call.application.log.error("Login error", e)
            call.respond(HttpStatusCode.Unauthorized, "Invalid credentials")
        }
    }

    suspend fun getMe(call: ApplicationCall) {
        try {
            val user = userService.getCurrentUser(call)
            val response = UserResponse(
                id = user.id.toString(),
                name = user.name,
                email = user.email,
                role = user.role
            )
            call.respond(response)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.Unauthorized, "Unauthorized")
        }
    }
}
