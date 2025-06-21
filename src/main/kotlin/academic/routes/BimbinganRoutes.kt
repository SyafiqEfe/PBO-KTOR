package academic.routes

import academic.controllers.BimbinganController
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Route.bimbinganRoutes(controller: BimbinganController) {
    route("/api") {
        authenticate("auth-jwt") {
            route("/bimbingan") {
                get { controller.getAll(call) }
                post { controller.create(call) }
                delete("/{id}") { controller.delete(call) }
            }
        }
    }
}
