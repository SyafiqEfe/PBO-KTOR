package academic.dtos

import kotlinx.serialization.Serializable

@Serializable
data class MahasiswaUpdateRequest(
    val nama: String? = null,
    val email: String? = null,
    val telepon: String? = null,
    val programStudi: String? = null,
    val semester: Int? = null
)

