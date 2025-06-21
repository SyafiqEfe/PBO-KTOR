package academic.services

import academic.models.Relasi
import academic.repositories.BimbinganRepository
import java.util.*

class BimbinganService {
    private val bimbinganRepo = BimbinganRepository()

    fun getAll(): List<Relasi> = bimbinganRepo.getAll()

    fun getByDosenId(dosenId: UUID): List<Relasi> = bimbinganRepo.getByDosenId(dosenId)

    fun create(relasi: Relasi): Relasi = bimbinganRepo.create(relasi)

    fun delete(id: UUID): Boolean = bimbinganRepo.delete(id)
}
