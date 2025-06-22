package academic.services

import academic.models.KRS
import academic.models.KRSCreateRequest
import academic.repositories.KRSRepository
import java.util.*

class KRSService(
    private val krsRepo: KRSRepository = KRSRepository()
) {
    fun getAll(): List<KRS> = krsRepo.findAll()

    fun create(request: KRSCreateRequest): KRS {
        val krs = KRS(
            id = UUID.randomUUID().toString(),
            mahasiswaId = request.mahasiswaId,
            mataKuliahKode = request.mataKuliahKode
        )
        return krsRepo.create(krs)
    }

    fun delete(id: String): Boolean = krsRepo.delete(id)

    fun getByMahasiswa(mahasiswaId: String): List<KRS> = krsRepo.findByMahasiswaId(mahasiswaId)
}
