package academic.dtos

import kotlinx.serialization.Serializable

@Serializable
data class MahasiswaCreateRequest(
    val nama: String,
    val email: String,
    val telepon: String,
    val nim: String,
    val programStudi: String,
    val semester: Int
)
