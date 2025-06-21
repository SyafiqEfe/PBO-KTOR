package academic.controllers

import academic.models.*
import academic.dtos.*
import academic.repositories.*
import academic.exceptions.AcademicException
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.UUID

class DosenController(
    private val dosenRepo: DosenRepository,
    private val mhsRepo: MahasiswaRepository
) {
    suspend fun getAll(call: ApplicationCall) {
        call.respond(dosenRepo.getAll())
    }

    suspend fun getById(call: ApplicationCall) {
        val id = call.parameters["id"] ?: throw AcademicException("ID required")
        call.respond(dosenRepo.getById(id) ?: throw AcademicException("Dosen not found"))
    }

    suspend fun create(call: ApplicationCall) {
        val request = call.receive<DosenCreateRequest>()
        val dosen = Dosen(
            id = UUID.randomUUID().toString(),
            nama = request.nama,
            email = request.email,
            telepon = request.telepon,
            nidn = request.nidn,
            departemen = request.departemen
        )
        dosenRepo.create(dosen)
        call.respond(mapOf("message" to "Dosen created", "id" to dosen.id))
    }

    suspend fun update(call: ApplicationCall) {
        val id = call.parameters["id"] ?: throw AcademicException("ID required")
        val request = call.receive<DosenUpdateRequest>()
        dosenRepo.update(id, request)
        call.respond(mapOf("message" to "Dosen updated"))
    }

    suspend fun delete(call: ApplicationCall) {
        val id = call.parameters["id"] ?: throw AcademicException("ID required")
        dosenRepo.delete(id)
        call.respond(mapOf("message" to "Dosen deleted"))
    }

    suspend fun addBimbingan(call: ApplicationCall) {
        val dosenId = call.parameters["id"] ?: throw AcademicException("Dosen ID required")
        val mhsId = call.parameters["mhsId"] ?: throw AcademicException("Mahasiswa ID required")
        
        val dosen = dosenRepo.getById(dosenId) ?: throw AcademicException("Dosen not found")
        val mhs = mhsRepo.getById(mhsId) ?: throw AcademicException("Mahasiswa not found")
        
        dosenRepo.addBimbingan(dosenId, mhsId)
        call.respond(mapOf("message" to "Mahasiswa added to bimbingan"))
    }

    suspend fun removeBimbingan(call: ApplicationCall) {
        val dosenId = call.parameters["id"] ?: throw AcademicException("Dosen ID required")
        val mhsId = call.parameters["mhsId"] ?: throw AcademicException("Mahasiswa ID required")
        
        dosenRepo.removeBimbingan(dosenId, mhsId)
        call.respond(mapOf("message" to "Mahasiswa removed from bimbingan"))
    }
}
