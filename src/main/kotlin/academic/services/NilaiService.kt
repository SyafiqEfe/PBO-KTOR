package academic.services

import academic.models.Nilai
import academic.models.NilaiCreateRequest
import academic.models.NilaiUpdateRequest
import academic.repositories.NilaiRepository
import java.util.*

class NilaiService(
    private val nilaiRepo: NilaiRepository = NilaiRepository()
) {
    fun getAll(): List<Nilai> = nilaiRepo.findAll()

    fun create(request: NilaiCreateRequest): Nilai {
        val nilai = Nilai(
            id = UUID.randomUUID().toString(),
            mahasiswaId = request.mahasiswaId,
            mataKuliahKode = request.mataKuliahKode,
            nilaiAngka = request.nilaiAngka,
            nilaiHuruf = request.nilaiHuruf
        )
        return nilaiRepo.create(nilai)
    }

    fun update(id: String, request: NilaiUpdateRequest): Boolean = nilaiRepo.update(id, request)

    fun delete(id: String): Boolean = nilaiRepo.delete(id)

    fun getByMahasiswa(mahasiswaId: String): List<Nilai> = nilaiRepo.findByMahasiswaId(mahasiswaId)
}
