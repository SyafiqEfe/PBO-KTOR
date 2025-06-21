package academic.dtos

import kotlinx.serialization.Serializable

@Serializable
data class MataKuliahUpdateRequest(
    val nama: String? = null,
    val sks: Int? = null,
    val semester: Int? = null,
    val deskripsi: String? = null
)
