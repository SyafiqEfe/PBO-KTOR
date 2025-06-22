package academic.routes

import academic.controllers.MahasiswaController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.mahasiswaRoutes(controller: MahasiswaController) {
    route("/mahasiswa") {
        get { controller.getAllMahasiswa(call) }
        post { controller.createMahasiswa(call) }
        
        route("/{id}") {
            get { controller.getMahasiswaById(call) }
            put { controller.updateMahasiswa(call) }
            delete { controller.deleteMahasiswa(call) }
            
            post("/ambil/{matkulKode}") { controller.ambilMatkul(call) }
            delete("/drop/{matkulKode}") { controller.dropMatkul(call) }
        }
    }
}
