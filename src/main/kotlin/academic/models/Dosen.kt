package academic.models

import kotlinx.serialization.Serializable

@Serializable
data class Dosen(
    val id: String,
    val nama: String,
    val nidn: String,
    val email: String,
    val telepon: String,
    val departemen: String,
    val password: String
)

@Serializable
data class DosenResponse(
    val id: String,
    val nama: String,
    val nidn: String
)

@Serializable
data class DosenCreateRequest(
    val nama: String,
    val nidn: String,
    val email: String,
    val telepon: String,
    val departemen: String,
    val password: String
)

@Serializable
data class DosenUpdateRequest(
    val nama: String? = null,
    val password: String? = null
)
