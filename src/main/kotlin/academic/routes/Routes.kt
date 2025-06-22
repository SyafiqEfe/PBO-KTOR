package academic.routes

import academic.controllers.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    authController: AuthController,
    mahasiswaController: MahasiswaController,
    dosenController: DosenController,
    matakuliahController: MataKuliahController
) {
    routing {
        // Public routes
        route("/api") {
            post("/login") { authController.login(call) }
        }
        
        // Protected routes
        authenticate("auth-jwt") {
            route("/api") {
                // Mahasiswa routes
                route("/mahasiswa") {
                    get { mahasiswaController.getAllMahasiswa(call) }
                    post { mahasiswaController.createMahasiswa(call) }
                    get("/{id}") { mahasiswaController.getMahasiswaById(call) }
                    put("/{id}") { mahasiswaController.updateMahasiswa(call) }
                    delete("/{id}") { mahasiswaController.deleteMahasiswa(call) }
                }
                
                // Dosen routes
                route("/dosen") {
                    get { dosenController.getAllDosen(call) }
                    post { dosenController.createDosen(call) }
                    get("/{id}") { dosenController.getDosenById(call) }
                    put("/{id}") { dosenController.updateDosen(call) }
                    delete("/{id}") { dosenController.deleteDosen(call) }
                }
                
                // Mata Kuliah routes
                route("/matakuliah") {
                    get { matakuliahController.getAll(call) }
                    post { matakuliahController.create(call) }
                    get("/{kode}") { matakuliahController.getByKode(call) }
                    put("/{kode}") { matakuliahController.update(call) }
                    delete("/{kode}") { matakuliahController.delete(call) }
                }
            }
        }
    }
}
