package academic.controllers

import academic.models.*
import academic.dtos.*
import academic.repositories.*
import academic.exceptions.AcademicException
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.UUID

class MataKuliahController(private val repo: MataKuliahRepository) {
    suspend fun getAll(call: ApplicationCall) {
        call.respond(repo.getAll())
    }

    suspend fun getByKode(call: ApplicationCall) {
        val kode = call.parameters["kode"] ?: throw AcademicException("Kode required")
        call.respond(repo.getByKode(kode) ?: throw AcademicException("Mata kuliah not found"))
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
        repo.create(matkul)
        call.respond(mapOf("message" to "Mata kuliah created"))
    }

    suspend fun update(call: ApplicationCall) {
        val kode = call.parameters["kode"] ?: throw AcademicException("Kode required")
        val request = call.receive<MataKuliahUpdateRequest>()
        repo.update(kode, request)
        call.respond(mapOf("message" to "Mata kuliah updated"))
    }

    suspend fun delete(call: ApplicationCall) {
        val kode = call.parameters["kode"] ?: throw AcademicException("Kode required")
        repo.delete(kode)
        call.respond(mapOf("message" to "Mata kuliah deleted"))
    }
}
