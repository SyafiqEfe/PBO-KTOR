package academic.dtos

import kotlinx.serialization.Serializable

@Serializable
data class MahasiswaCreateRequest(
    val nama: String,
    val nim: String,
    val email: String,
    val telepon: String,
    val programStudi: String,
    val semester: Int
)
