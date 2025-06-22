package academic.controllers

import academic.dtos.LoginRequest
import academic.models.LoginResponse
import academic.models.UserInfo
import academic.services.UserService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.http.*

class AuthController(private val userService: UserService) {
    
    suspend fun login(call: ApplicationCall) {
        try {
            val request = call.receive<LoginRequest>()
            val response = userService.login(request)
            call.respond(response)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.Unauthorized, mapOf("error" to e.message))
        }
    }
}
