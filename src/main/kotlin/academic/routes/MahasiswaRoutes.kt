package academic.routes

import academic.controllers.MahasiswaController
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Route.mahasiswaRoutes(controller: MahasiswaController) {
    route("/api") {
        authenticate("auth-jwt") {
            route("/mahasiswa") {
                get { controller.getAll(call) }
                post { controller.create(call) }
                get("/{id}") { controller.getById(call) }
                put("/{id}") { controller.update(call) }
                delete("/{id}") { controller.delete(call) }
                post("/{id}/matkul") { controller.ambilMatkul(call) }
                delete("/{id}/matkul/{kode}") { controller.dropMatkul(call) }
            }
        }
    }
}
