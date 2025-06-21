package academic.dtos

import kotlinx.serialization.Serializable

@Serializable
data class DosenUpdateRequest(
    val nama: String? = null,
    val email: String? = null,
    val telepon: String? = null,
    val departemen: String? = null
)
