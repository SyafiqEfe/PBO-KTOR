package academic.controllers

import academic.dtos.DosenCreateRequest
import academic.dtos.DosenUpdateRequest
import academic.models.Dosen
import academic.services.DosenService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class DosenController(private val service: DosenService) {
    suspend fun getAll(call: ApplicationCall) {
        call.respond(service.getAll())
    }

    suspend fun getById(call: ApplicationCall) {
        val id = UUID.fromString(call.parameters["id"]!!)
        val dosen = service.getById(id)
        if (dosen != null) {
            call.respond(dosen)
        } else {
            call.respond(io.ktor.http.HttpStatusCode.NotFound, "Dosen not found")
        }
    }

    suspend fun create(call: ApplicationCall) {
        val request = call.receive<DosenCreateRequest>()
        val dosen = Dosen(
            id = UUID.randomUUID(),
            nama = request.nama,
            nidn = request.nidn,
            email = request.email,
            telepon = request.telepon,
            departemen = request.departemen
        )
        call.respond(service.create(dosen))
    }

    suspend fun update(call: ApplicationCall) {
        val id = UUID.fromString(call.parameters["id"]!!)
        val request = call.receive<DosenUpdateRequest>()
        val existing = service.getById(id) ?: run {
            call.respond(io.ktor.http.HttpStatusCode.NotFound, "Dosen not found")
            return
        }
        
        val updated = Dosen(
            id = existing.id,
            nama = request.nama ?: existing.nama,
            nidn = existing.nidn,
            email = request.email ?: existing.email,
            telepon = request.telepon ?: existing.telepon,
            departemen = request.departemen ?: existing.departemen
        )
        call.respond(service.update(id, updated))
    }

    suspend fun delete(call: ApplicationCall) {
        val id = UUID.fromString(call.parameters["id"]!!)
        call.respond(service.delete(id))
    }

    suspend fun addBimbingan(call: ApplicationCall) {
        val dosenId = UUID.fromString(call.parameters["id"]!!)
        val mhsId = UUID.fromString(call.parameters["mhsId"]!!)
        call.respond(service.addBimbingan(dosenId, mhsId))
    }

    suspend fun removeBimbingan(call: ApplicationCall) {
        val dosenId = UUID.fromString(call.parameters["id"]!!)
        val mhsId = UUID.fromString(call.parameters["mhsId"]!!)
        call.respond(service.removeBimbingan(dosenId, mhsId))
    }
}
