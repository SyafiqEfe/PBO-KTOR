package academic.controllers

import academic.models.KRSCreateRequest
import academic.services.KRSService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class KRSController(private val service: KRSService) {
    
    suspend fun getAll(call: ApplicationCall) {
        try {
            val krs = service.getAll()
            call.respond(HttpStatusCode.OK, krs)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, mapOf("error" to e.message))
        }
    }

    suspend fun create(call: ApplicationCall) {
        try {
            val request = call.receive<KRSCreateRequest>()
            val krs = service.create(request)
            call.respond(HttpStatusCode.Created, krs)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
        }
    }

    suspend fun delete(call: ApplicationCall) {
        try {
            val id = call.parameters["id"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing ID")
            val deleted = service.delete(id)
            if (deleted) {
                call.respond(HttpStatusCode.OK, "KRS deleted successfully")
            } else {
                call.respond(HttpStatusCode.NotFound, "KRS not found")
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, mapOf("error" to e.message))
        }
    }

    suspend fun getByMahasiswa(call: ApplicationCall) {
        try {
            val mahasiswaId = call.parameters["mahasiswaId"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing mahasiswa ID")
            val krs = service.getByMahasiswa(mahasiswaId)
            call.respond(HttpStatusCode.OK, krs)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, mapOf("error" to e.message))
        }
    }
}
