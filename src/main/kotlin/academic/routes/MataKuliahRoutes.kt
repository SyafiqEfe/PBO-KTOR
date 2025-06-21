package academic.routes

import academic.controllers.MataKuliahController
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Route.mataKuliahRoutes(controller: MataKuliahController) {
    route("/api") {
        authenticate("auth-jwt") {
            route("/matakuliah") {
                get { controller.getAll(call) }
                post { controller.create(call) }
                get("/{kode}") { controller.getByKode(call) }
                put("/{kode}") { controller.update(call) }
                delete("/{kode}") { controller.delete(call) }
            }
        }
    }
}
