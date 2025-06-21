package academic.repositories

import academic.models.*
import academic.dtos.MataKuliahUpdateRequest
import academic.exceptions.AcademicException
import java.util.concurrent.ConcurrentHashMap

class MataKuliahRepository {
    private val db = ConcurrentHashMap<String, MataKuliah>()

    fun getAll(): List<MataKuliah> = db.values.toList()
    fun getByKode(kode: String): MataKuliah? = db[kode]

    fun create(matkul: MataKuliah) {
        if (db.containsKey(matkul.kode)) {
            throw AcademicException("Kode mata kuliah sudah ada")
        }
        db[matkul.kode] = matkul
    }

    fun update(kode: String, request: MataKuliahUpdateRequest) {
        val existing = db[kode] ?: throw AcademicException("Mata kuliah not found")
        db[kode] = existing.copy(
            nama = request.nama ?: existing.nama,
            sks = request.sks ?: existing.sks,
            semester = request.semester ?: existing.semester,
            deskripsi = request.deskripsi ?: existing.deskripsi
        )
    }

    fun delete(kode: String) {
        db.remove(kode) ?: throw AcademicException("Mata kuliah not found")
    }
}
