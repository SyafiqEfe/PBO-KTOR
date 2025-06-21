package academic.services

import academic.models.MataKuliah
import academic.repositories.MataKuliahRepository

class MataKuliahService {
    private val mkRepo = MataKuliahRepository()

    fun getAll(): List<MataKuliah> = mkRepo.getAll()

    fun getByKode(kode: String): MataKuliah? = mkRepo.getByKode(kode)

    fun create(mk: MataKuliah): MataKuliah = mkRepo.create(mk)

    fun update(kode: String, updated: MataKuliah): Boolean = mkRepo.update(kode, updated)

    fun delete(kode: String): Boolean = mkRepo.delete(kode)
}
