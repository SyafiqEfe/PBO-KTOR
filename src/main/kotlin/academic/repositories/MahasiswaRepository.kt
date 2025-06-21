package academic.repositories

import academic.models.*
import academic.dtos.MahasiswaUpdateRequest
import academic.exceptions.AcademicException
import java.util.concurrent.ConcurrentHashMap

class MahasiswaRepository {
    private val db = ConcurrentHashMap<String, Mahasiswa>()

    fun getAll(): List<Mahasiswa> = db.values.toList()
    fun getById(id: String): Mahasiswa? = db[id]
    
    fun create(mhs: Mahasiswa) {
        if (db.values.any { it.nim == mhs.nim }) {
            throw AcademicException("NIM already exists")
        }
        db[mhs.id] = mhs
    }

    fun update(id: String, request: MahasiswaUpdateRequest) {
        val existing = db[id] ?: throw AcademicException("Mahasiswa not found")
        db[id] = existing.copy(
            nama = request.nama ?: existing.nama,
            email = request.email ?: existing.email,
            telepon = request.telepon ?: existing.telepon,
            programStudi = request.programStudi ?: existing.programStudi,
            semester = request.semester ?: existing.semester
        )
    }

    fun delete(id: String) {
        db.remove(id) ?: throw AcademicException("Mahasiswa not found")
    }

    fun ambilMatkul(id: String, matkul: MataKuliah) {
        val mhs = db[id] ?: throw AcademicException("Mahasiswa not found")
        mhs.ambilMataKuliah(matkul)
    }

    fun dropMatkul(id: String, kode: String) {
        val mhs = db[id] ?: throw AcademicException("Mahasiswa not found")
        mhs.dropMataKuliah(kode)
    }
}
