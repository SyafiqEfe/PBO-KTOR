package academic.controllers

import academic.models.Relasi
import academic.services.BimbinganService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class BimbinganController(private val service: BimbinganService) {
    suspend fun getAll(call: ApplicationCall) {
        call.respond(service.getAll())
    }

    suspend fun getByDosenId(call: ApplicationCall) {
        val id = UUID.fromString(call.parameters["id"]!!)
        call.respond(service.getByDosenId(id))
    }

    suspend fun create(call: ApplicationCall) {
        val relasi = call.receive<Relasi>()
        call.respond(service.create(relasi))
    }

    suspend fun delete(call: ApplicationCall) {
        val id = UUID.fromString(call.parameters["id"]!!)
        call.respond(service.delete(id))
    }
}
