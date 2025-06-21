package academic.services

import academic.models.Mahasiswa
import academic.repositories.MahasiswaRepository
import academic.repositories.MahasiswaMatkulRepository
import academic.repositories.MataKuliahRepository
import academic.dtos.MahasiswaUpdateRequest
import java.util.*

class MahasiswaService(
    private val mhsRepo: MahasiswaRepository = MahasiswaRepository(),
    private val matkulRepo: MataKuliahRepository = MataKuliahRepository(),
    private val relasiRepo: MahasiswaMatkulRepository = MahasiswaMatkulRepository()
) {
    fun getAll(): List<Mahasiswa> = mhsRepo.getAll()

    fun getById(id: UUID): Mahasiswa? = mhsRepo.getById(id)

    fun create(mhs: Mahasiswa): Mahasiswa = mhsRepo.create(mhs)

    fun update(id: UUID, updated: Mahasiswa): Boolean = mhsRepo.update(id, updated)

    fun updateFromRequest(id: UUID, request: MahasiswaUpdateRequest): Boolean {
        val existing = mhsRepo.getById(id) ?: return false
        val updated = Mahasiswa(
            id = existing.id,
            nama = request.nama ?: existing.nama,
            nim = existing.nim,
            email = request.email ?: existing.email,
            telepon = request.telepon ?: existing.telepon,
            programStudi = request.programStudi ?: existing.programStudi,
            semester = request.semester ?: existing.semester
        )
        return mhsRepo.update(id, updated)
    }

    fun delete(id: UUID): Boolean = mhsRepo.delete(id)

    fun ambilMatkul(mahasiswaId: UUID, matkulKode: String): String {
        if (mhsRepo.getById(mahasiswaId) == null) throw Exception("Mahasiswa tidak ditemukan")
        if (matkulRepo.getByKode(matkulKode) == null) throw Exception("Mata kuliah tidak ditemukan")
        relasiRepo.ambilMatkul(mahasiswaId, matkulKode)
        return "Mata kuliah berhasil diambil"
    }

    fun dropMatkul(mahasiswaId: UUID, matkulKode: String): String {
        relasiRepo.dropMatkul(mahasiswaId, matkulKode)
        return "Mata kuliah berhasil di-drop"
    }
}
