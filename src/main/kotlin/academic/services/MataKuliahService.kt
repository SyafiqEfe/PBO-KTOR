package academic.services

import academic.models.MataKuliah
import academic.models.MataKuliahCreateRequest
import academic.models.MataKuliahUpdateRequest
import academic.repositories.MataKuliahRepository

class MataKuliahService(
    private val mataKuliahRepo: MataKuliahRepository = MataKuliahRepository()
) {
    fun getAll(): List<MataKuliah> = mataKuliahRepo.findAll()

    fun getByKode(kode: String): MataKuliah? = mataKuliahRepo.findByKode(kode)

    fun create(request: MataKuliahCreateRequest): MataKuliah = mataKuliahRepo.create(request)

    fun update(kode: String, request: MataKuliahUpdateRequest): Boolean = 
        mataKuliahRepo.update(kode, request)

    fun delete(kode: String): Boolean = mataKuliahRepo.delete(kode)
}
