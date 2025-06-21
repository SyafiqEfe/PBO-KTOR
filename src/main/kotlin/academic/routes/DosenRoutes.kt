package academic.routes

import academic.controllers.DosenController
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Route.dosenRoutes(controller: DosenController) {
    route("/api") {
        authenticate("auth-jwt") {
            route("/dosen") {
                get { controller.getAll(call) }
                post { controller.create(call) }
                get("/{id}") { controller.getById(call) }
                put("/{id}") { controller.update(call) }
                delete("/{id}") { controller.delete(call) }
                post("/{id}/bimbingan/{mhsId}") { controller.addBimbingan(call) }
                delete("/{id}/bimbingan/{mhsId}") { controller.removeBimbingan(call) }
            }
        }
    }
}
