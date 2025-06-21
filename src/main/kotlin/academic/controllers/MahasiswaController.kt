package academic.controllers

import academic.models.*
import academic.dtos.*
import academic.repositories.*
import academic.exceptions.AcademicException
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.UUID

class MahasiswaController(
    private val repo: MahasiswaRepository,
    private val matkulRepo: MataKuliahRepository
) {
    suspend fun getAll(call: ApplicationCall) {
        call.respond(repo.getAll())
    }

    suspend fun getById(call: ApplicationCall) {
        val id = call.parameters["id"] ?: throw AcademicException("ID required")
        call.respond(repo.getById(id) ?: throw AcademicException("Mahasiswa not found"))
    }

    suspend fun create(call: ApplicationCall) {
        val request = call.receive<MahasiswaCreateRequest>()
        val mhs = Mahasiswa(
            id = UUID.randomUUID().toString(),
            nama = request.nama,
            email = request.email,
            telepon = request.telepon,
            nim = request.nim,
            programStudi = request.programStudi,
            semester = request.semester
        )
        repo.create(mhs)
        call.respond(mapOf("message" to "Mahasiswa created", "id" to mhs.id))
    }

    suspend fun update(call: ApplicationCall) {
        val id = call.parameters["id"] ?: throw AcademicException("ID required")
        val request = call.receive<MahasiswaUpdateRequest>()
        repo.update(id, request)
        call.respond(mapOf("message" to "Mahasiswa updated"))
    }

    suspend fun delete(call: ApplicationCall) {
        val id = call.parameters["id"] ?: throw AcademicException("ID required")
        repo.delete(id)
        call.respond(mapOf("message" to "Mahasiswa deleted"))
    }

    suspend fun ambilMatkul(call: ApplicationCall) {
        val id = call.parameters["id"] ?: throw AcademicException("ID required")
        val request = call.receive<AmbilMatkulRequest>()
        val matkul = matkulRepo.getByKode(request.kode) 
            ?: throw AcademicException("Mata kuliah not found")
        repo.ambilMatkul(id, matkul)
        call.respond(mapOf("message" to "Mata kuliah diambil"))
    }

    suspend fun dropMatkul(call: ApplicationCall) {
        val id = call.parameters["id"] ?: throw AcademicException("ID required")
        val kode = call.parameters["kode"] ?: throw AcademicException("Kode required")
        repo.dropMatkul(id, kode)
        call.respond(mapOf("message" to "Mata kuliah di-drop"))
    }
}
