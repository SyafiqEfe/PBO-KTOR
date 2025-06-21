package academic.controllers

import academic.dtos.MahasiswaUpdateRequest
import academic.dtos.AmbilMatkulRequest
import academic.models.Mahasiswa
import academic.services.MahasiswaService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class MahasiswaController(private val service: MahasiswaService) {
    suspend fun getAll(call: ApplicationCall) {
        call.respond(service.getAll())
    }

    suspend fun getById(call: ApplicationCall) {
        val id = UUID.fromString(call.parameters["id"]!!)
        val mahasiswa = service.getById(id)
        if (mahasiswa != null) {
            call.respond(mahasiswa)
        } else {
            call.respond(io.ktor.http.HttpStatusCode.NotFound, "Mahasiswa not found")
        }
    }

    suspend fun create(call: ApplicationCall) {
        val mahasiswa = call.receive<Mahasiswa>()
        call.respond(service.create(mahasiswa))
    }

    suspend fun update(call: ApplicationCall) {
        val id = UUID.fromString(call.parameters["id"]!!)
        val request = call.receive<MahasiswaUpdateRequest>()
        val result = service.updateFromRequest(id, request)
        call.respond(result)
    }

    suspend fun delete(call: ApplicationCall) {
        val id = UUID.fromString(call.parameters["id"]!!)
        call.respond(service.delete(id))
    }

    suspend fun ambilMatkul(call: ApplicationCall) {
        val id = UUID.fromString(call.parameters["id"]!!)
        val request = call.receive<AmbilMatkulRequest>()
        call.respond(service.ambilMatkul(id, request.kode))
    }

    suspend fun dropMatkul(call: ApplicationCall) {
        val id = UUID.fromString(call.parameters["id"]!!)
        val kode = call.parameters["kode"]!!
        call.respond(service.dropMatkul(id, kode))
    }
}
