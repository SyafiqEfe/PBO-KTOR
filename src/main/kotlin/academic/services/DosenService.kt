package academic.services

import academic.models.Dosen
import academic.repositories.DosenRepository
import academic.repositories.MahasiswaRepository
import java.util.*

class DosenService(
    private val dosenRepo: DosenRepository = DosenRepository(),
    private val mhsRepo: MahasiswaRepository = MahasiswaRepository()
) {
    fun getAll(): List<Dosen> = dosenRepo.getAll()

    fun getById(id: UUID): Dosen? = dosenRepo.getById(id)

    fun create(dosen: Dosen): Dosen = dosenRepo.create(dosen)

    fun update(id: UUID, updated: Dosen): Boolean = dosenRepo.update(id, updated)

    fun delete(id: UUID): Boolean = dosenRepo.delete(id)

    fun addBimbingan(dosenId: UUID, mhsId: UUID): String {
        if (dosenRepo.getById(dosenId) == null) throw Exception("Dosen tidak ditemukan")
        if (mhsRepo.getById(mhsId) == null) throw Exception("Mahasiswa tidak ditemukan")
        return "Bimbingan added successfully"
    }

    fun removeBimbingan(dosenId: UUID, mhsId: UUID): String {
        return "Bimbingan removed successfully"
    }
}
