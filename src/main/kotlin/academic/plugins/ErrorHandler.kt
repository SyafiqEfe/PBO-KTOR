package academic.plugins

import academic.exceptions.ValidationException
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.http.*

fun Application.configureErrorHandling() {
    install(StatusPages) {
        exception<ValidationException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to cause.message))
        }
        exception<Throwable> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError, mapOf("error" to (cause.message ?: "Internal server error")))
        }
    }
}
