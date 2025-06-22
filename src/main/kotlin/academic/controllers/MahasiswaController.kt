package academic.controllers

import academic.models.*
import academic.services.MahasiswaService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class MahasiswaController(private val mahasiswaService: MahasiswaService) {

    suspend fun getAllMahasiswa(call: ApplicationCall) {
        try {
            val mahasiswa = mahasiswaService.getAllMahasiswa()
            call.respond(HttpStatusCode.OK, mahasiswa)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, mapOf("error" to e.message))
        }
    }

    suspend fun getMahasiswaById(call: ApplicationCall) {
        try {
            val id = call.parameters["id"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing ID")
            val mahasiswa = mahasiswaService.getMahasiswaById(id)
            if (mahasiswa != null) {
                call.respond(HttpStatusCode.OK, mahasiswa)
            } else {
                call.respond(HttpStatusCode.NotFound, "Mahasiswa not found")
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, mapOf("error" to e.message))
        }
    }

    suspend fun createMahasiswa(call: ApplicationCall) {
        try {
            val request = call.receive<MahasiswaCreateRequest>()
            val mahasiswa = mahasiswaService.createMahasiswa(request)
            call.respond(HttpStatusCode.Created, mahasiswa)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
        }
    }

    suspend fun updateMahasiswa(call: ApplicationCall) {
        try {
            val id = call.parameters["id"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing ID")
            val request = call.receive<MahasiswaUpdateRequest>()
            val mahasiswa = mahasiswaService.updateMahasiswa(id, request)
            if (mahasiswa != null) {
                call.respond(HttpStatusCode.OK, mahasiswa)
            } else {
                call.respond(HttpStatusCode.NotFound, "Mahasiswa not found")
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
        }
    }

    suspend fun deleteMahasiswa(call: ApplicationCall) {
        try {
            val id = call.parameters["id"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing ID")
            val deleted = mahasiswaService.deleteMahasiswa(id)
            if (deleted) {
                call.respond(HttpStatusCode.OK, "Mahasiswa deleted successfully")
            } else {
                call.respond(HttpStatusCode.NotFound, "Mahasiswa not found")
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, mapOf("error" to e.message))
        }
    }

    suspend fun ambilMatkul(call: ApplicationCall) {
        try {
            val mahasiswaId = call.parameters["id"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing mahasiswa ID")
            val matkulKode = call.parameters["matkulKode"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing matkul code")
            
            mahasiswaService.ambilMatkul(mahasiswaId, matkulKode)
            call.respond(HttpStatusCode.OK, "Mata kuliah berhasil diambil")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
        }
    }

    suspend fun dropMatkul(call: ApplicationCall) {
        try {
            val mahasiswaId = call.parameters["id"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing mahasiswa ID")
            val matkulKode = call.parameters["matkulKode"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing matkul code")
            
            mahasiswaService.dropMatkul(mahasiswaId, matkulKode)
            call.respond(HttpStatusCode.OK, "Mata kuliah berhasil di-drop")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
        }
    }
}
