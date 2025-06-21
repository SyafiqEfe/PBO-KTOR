package academic.dtos

import kotlinx.serialization.Serializable

@Serializable
data class DosenCreateRequest(
    val nama: String,
    val email: String,
    val telepon: String,
    val nidn: String,
    val departemen: String
)
