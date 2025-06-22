package academic.routes

import academic.controllers.NilaiController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.nilaiRoutes(controller: NilaiController) {
    route("/api/nilai") {
        get { controller.getAll(call) }
        post { controller.create(call) }
        put("/{id}") { controller.update(call) }
        delete("/{id}") { controller.delete(call) }
        get("/mahasiswa/{mahasiswaId}") { controller.getByMahasiswa(call) }
    }
}
