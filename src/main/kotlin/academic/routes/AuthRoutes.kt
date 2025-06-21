package academic.routes

import academic.controllers.AuthController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.authRoutes(controller: AuthController) {
    route("/api") {
        post("/login") { controller.login(call) }
        post("/register") { controller.register(call) }
        get("/me") { controller.getMe(call) }
    }
}
