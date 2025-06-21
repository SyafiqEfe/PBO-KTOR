package academic.repositories

import academic.models.Dosen
import academic.dtos.DosenUpdateRequest
import academic.exceptions.AcademicException
import java.util.concurrent.ConcurrentHashMap

class DosenRepository {
    private val db = ConcurrentHashMap<String, Dosen>()
    private val bimbinganMap = ConcurrentHashMap<String, MutableSet<String>>() // DosenID -> Set<MahasiswaID>

    fun getAll(): List<Dosen> = db.values.toList()

    fun getById(id: String): Dosen? = db[id]

    fun create(dosen: Dosen) {
        requireUniqueNidn(dosen.nidn)
        db[dosen.id] = dosen
    }

    fun update(id: String, request: DosenUpdateRequest) {
        val existing = getExistingDosen(id)
        db[id] = existing.copy(
            nama = request.nama ?: existing.nama,
            email = request.email ?: existing.email,
            telepon = request.telepon ?: existing.telepon,
            departemen = request.departemen ?: existing.departemen
        )
    }

    fun delete(id: String) {
        db.remove(id) ?: throw AcademicException("Dosen with ID $id not found")
        bimbinganMap.remove(id)
    }

    fun addBimbingan(dosenId: String, mhsId: String) {
        getExistingDosen(dosenId) // Verify dosen exists
        bimbinganMap.computeIfAbsent(dosenId) { mutableSetOf() }.add(mhsId)
    }

    fun removeBimbingan(dosenId: String, mhsId: String) {
        bimbinganMap[dosenId]?.remove(mhsId) 
            ?: throw AcademicException("Bimbingan not found for Dosen $dosenId and Mahasiswa $mhsId")
    }

    fun getBimbingan(dosenId: String): Set<String> {
        return bimbinganMap[dosenId] ?: emptySet()
    }

    private fun getExistingDosen(id: String): Dosen {
        return db[id] ?: throw AcademicException("Dosen with ID $id not found")
    }

    private fun requireUniqueNidn(nidn: String) {
        if (db.values.any { it.nidn == nidn }) {
            throw AcademicException("NIDN $nidn already exists")
        }
    }
}
