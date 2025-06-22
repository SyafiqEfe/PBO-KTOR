package academic.services

import academic.models.*
import academic.repositories.DosenRepository
import java.util.*

class DosenService {
    private val dosenRepository = DosenRepository()

    fun getAllDosen(): List<DosenResponse> {
        return dosenRepository.findAll().map { dosen ->
            DosenResponse(
                id = dosen.id,
                nama = dosen.nama,
                nidn = dosen.nidn
            )
        }
    }

    fun getDosenById(id: String): Dosen? {
        return dosenRepository.findById(id)
    }

    fun createDosen(request: DosenCreateRequest): Dosen {
        return dosenRepository.create(request)
    }

    fun updateDosen(id: String, request: DosenUpdateRequest): Dosen? {
        val updated = dosenRepository.update(id, request)
        return if (updated) dosenRepository.findById(id) else null
    }

    fun deleteDosen(id: String): Boolean {
        return dosenRepository.delete(id)
    }
}
