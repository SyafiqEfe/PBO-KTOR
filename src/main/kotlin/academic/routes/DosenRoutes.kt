package academic.routes

import academic.controllers.DosenController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.dosenRoutes(controller: DosenController) {
    route("/dosen") {
        get { controller.getAllDosen(call) }
        post { controller.createDosen(call) }
        
        route("/{id}") {
            get { controller.getDosenById(call) }
            put { controller.updateDosen(call) }
            delete { controller.deleteDosen(call) }
        }
    }
}
