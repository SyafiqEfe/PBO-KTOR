package academic.services

import academic.models.*
import academic.repositories.MahasiswaRepository
import academic.repositories.MahasiswaMatkulRepository
import java.util.*

class MahasiswaService {
    private val mahasiswaRepository = MahasiswaRepository()
    private val mahasiswaMatkulRepository = MahasiswaMatkulRepository()

    fun getAllMahasiswa(): List<MahasiswaResponse> {
        return mahasiswaRepository.findAll().map { mahasiswa ->
            MahasiswaResponse(
                id = mahasiswa.id,
                nama = mahasiswa.nama,
                nim = mahasiswa.nim,
                kelas = mahasiswa.kelas
            )
        }
    }

    fun getMahasiswaById(id: String): Mahasiswa? {
        return mahasiswaRepository.findById(id)
    }

    fun createMahasiswa(request: MahasiswaCreateRequest): Mahasiswa {
        return mahasiswaRepository.create(request)
    }

    fun updateMahasiswa(id: String, request: MahasiswaUpdateRequest): Mahasiswa? {
        val updated = mahasiswaRepository.update(id, request)
        return if (updated) mahasiswaRepository.findById(id) else null
    }

    fun deleteMahasiswa(id: String): Boolean {
        return mahasiswaRepository.delete(id)
    }

    fun ambilMatkul(mahasiswaId: String, matkulKode: String) {
        mahasiswaMatkulRepository.ambilMatkul(mahasiswaId, matkulKode)
    }

    fun dropMatkul(mahasiswaId: String, matkulKode: String) {
        mahasiswaMatkulRepository.dropMatkul(mahasiswaId, matkulKode)
    }

    fun getMatkulMahasiswa(mahasiswaId: String): List<String> {
        return mahasiswaMatkulRepository.getMatkulByMahasiswa(mahasiswaId)
    }
}
