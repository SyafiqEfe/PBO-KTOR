package academic.controllers

import academic.models.MataKuliahCreateRequest
import academic.models.MataKuliahUpdateRequest
import academic.services.MataKuliahService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.http.*

class MataKuliahController(private val mataKuliahService: MataKuliahService) {
    
    suspend fun getAll(call: ApplicationCall) {
        try {
            val mataKuliah = mataKuliahService.getAll()
            call.respond(mataKuliah)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, mapOf("error" to e.message))
        }
    }
    
    suspend fun getByKode(call: ApplicationCall) {
        try {
            val kode = call.parameters["kode"] ?: throw IllegalArgumentException("Missing kode")
            val mataKuliah = mataKuliahService.getByKode(kode)
            if (mataKuliah != null) {
                call.respond(mataKuliah)
            } else {
                call.respond(HttpStatusCode.NotFound, mapOf("error" to "Mata kuliah not found"))
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
        }
    }
    
    suspend fun create(call: ApplicationCall) {
        try {
            val request = call.receive<MataKuliahCreateRequest>()
            val mataKuliah = mataKuliahService.create(request)
            call.respond(HttpStatusCode.Created, mataKuliah)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
        }
    }
    
    suspend fun update(call: ApplicationCall) {
        try {
            val kode = call.parameters["kode"] ?: throw IllegalArgumentException("Missing kode")
            val request = call.receive<MataKuliahUpdateRequest>()
            val success = mataKuliahService.update(kode, request)
            if (success) {
                call.respond(HttpStatusCode.OK, mapOf("message" to "Mata kuliah updated successfully"))
            } else {
                call.respond(HttpStatusCode.NotFound, mapOf("error" to "Mata kuliah not found"))
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
        }
    }
    
    suspend fun delete(call: ApplicationCall) {
        try {
            val kode = call.parameters["kode"] ?: throw IllegalArgumentException("Missing kode")
            val success = mataKuliahService.delete(kode)
            if (success) {
                call.respond(HttpStatusCode.OK, mapOf("message" to "Mata kuliah deleted successfully"))
            } else {
                call.respond(HttpStatusCode.NotFound, mapOf("error" to "Mata kuliah not found"))
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
        }
    }
}
