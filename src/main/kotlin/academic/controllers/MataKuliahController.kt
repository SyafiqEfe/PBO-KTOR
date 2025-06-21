package academic.controllers

import academic.dtos.MataKuliahCreateRequest
import academic.dtos.MataKuliahUpdateRequest
import academic.models.MataKuliah
import academic.services.MataKuliahService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class MataKuliahController(private val service: MataKuliahService) {
    suspend fun getAll(call: ApplicationCall) {
        call.respond(service.getAll())
    }

    suspend fun getByKode(call: ApplicationCall) {
        val kode = call.parameters["kode"]!!
        val matkul = service.getByKode(kode)
        if (matkul != null) {
            call.respond(matkul)
        } else {
            call.respond(io.ktor.http.HttpStatusCode.NotFound, "Mata kuliah not found")
        }
    }

    suspend fun create(call: ApplicationCall) {
        val request = call.receive<MataKuliahCreateRequest>()
        val matkul = MataKuliah(
            kode = request.kode,
            nama = request.nama,
            sks = request.sks,
            semester = request.semester,
            deskripsi = request.deskripsi
        )
        call.respond(service.create(matkul))
    }

    suspend fun update(call: ApplicationCall) {
        val kode = call.parameters["kode"]!!
        val request = call.receive<MataKuliahUpdateRequest>()
        val existing = service.getByKode(kode) ?: run {
            call.respond(io.ktor.http.HttpStatusCode.NotFound, "Mata kuliah not found")
            return
        }
        
        val updated = MataKuliah(
            kode = existing.kode,
            nama = request.nama ?: existing.nama,
            sks = request.sks ?: existing.sks,
            semester = request.semester ?: existing.semester,
            deskripsi = request.deskripsi ?: existing.deskripsi
        )
        call.respond(service.update(kode, updated))
    }

    suspend fun delete(call: ApplicationCall) {
        val kode = call.parameters["kode"]!!
        call.respond(service.delete(kode))
    }
}
