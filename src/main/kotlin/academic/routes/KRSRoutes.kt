package academic.routes

import academic.controllers.KRSController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.krsRoutes(controller: KRSController) {
    route("/api/krs") {
        get { controller.getAll(call) }
        post { controller.create(call) }
        delete("/{id}") { controller.delete(call) }
        get("/mahasiswa/{mahasiswaId}") { controller.getByMahasiswa(call) }
    }
}
