package academic.controllers

import academic.models.*
import academic.services.DosenService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class DosenController(private val dosenService: DosenService) {

    suspend fun getAllDosen(call: ApplicationCall) {
        try {
            val dosen = dosenService.getAllDosen()
            call.respond(HttpStatusCode.OK, dosen)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, mapOf("error" to e.message))
        }
    }

    suspend fun getDosenById(call: ApplicationCall) {
        try {
            val id = call.parameters["id"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing ID")
            val dosen = dosenService.getDosenById(id)
            if (dosen != null) {
                call.respond(HttpStatusCode.OK, dosen)
            } else {
                call.respond(HttpStatusCode.NotFound, "Dosen not found")
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, mapOf("error" to e.message))
        }
    }

    suspend fun createDosen(call: ApplicationCall) {
        try {
            val request = call.receive<DosenCreateRequest>()
            val dosen = dosenService.createDosen(request)
            call.respond(HttpStatusCode.Created, dosen)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
        }
    }

    suspend fun updateDosen(call: ApplicationCall) {
        try {
            val id = call.parameters["id"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing ID")
            val request = call.receive<DosenUpdateRequest>()
            val dosen = dosenService.updateDosen(id, request)
            if (dosen != null) {
                call.respond(HttpStatusCode.OK, dosen)
            } else {
                call.respond(HttpStatusCode.NotFound, "Dosen not found")
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
        }
    }

    suspend fun deleteDosen(call: ApplicationCall) {
        try {
            val id = call.parameters["id"] ?: return call.respond(HttpStatusCode.BadRequest, "Missing ID")
            val deleted = dosenService.deleteDosen(id)
            if (deleted) {
                call.respond(HttpStatusCode.OK, "Dosen deleted successfully")
            } else {
                call.respond(HttpStatusCode.NotFound, "Dosen not found")
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, mapOf("error" to e.message))
        }
    }
}
