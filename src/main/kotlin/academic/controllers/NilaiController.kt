package academic.controllers

import academic.models.NilaiCreateRequest
import academic.models.NilaiUpdateRequest
import academic.services.NilaiService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class NilaiController(private val service: NilaiService) {
    
    suspend fun getAll(call: ApplicationCall) {
        try {
            val nilai = service.getAll()
            call.respond(HttpStatusCode.OK, nilai)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, mapOf("error" to e.message))
        }
    }

    suspend fun create(call: ApplicationCall) {
        try {
            val request = call.receive<NilaiCreateRequest>()
            val nilai = service.create(request)
            call.respond(HttpStatusCode.Created, nilai)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
        }
    }

    suspend fun update(call: ApplicationCall) {
        try {
            val id = call.parameters["id"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing ID")
            val request = call.receive<NilaiUpdateRequest>()
            val updated = service.update(id, request)
            if (updated) {
                call.respond(HttpStatusCode.OK, "Nilai updated successfully")
            } else {
                call.respond(HttpStatusCode.NotFound, "Nilai not found")
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
        }
    }

    suspend fun delete(call: ApplicationCall) {
        try {
            val id = call.parameters["id"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing ID")
            val deleted = service.delete(id)
            if (deleted) {
                call.respond(HttpStatusCode.OK, "Nilai deleted successfully")
            } else {
                call.respond(HttpStatusCode.NotFound, "Nilai not found")
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, mapOf("error" to e.message))
        }
    }

    suspend fun getByMahasiswa(call: ApplicationCall) {
        try {
            val mahasiswaId = call.parameters["mahasiswaId"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing mahasiswa ID")
            val nilai = service.getByMahasiswa(mahasiswaId)
            call.respond(HttpStatusCode.OK, nilai)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, mapOf("error" to e.message))
        }
    }
}
