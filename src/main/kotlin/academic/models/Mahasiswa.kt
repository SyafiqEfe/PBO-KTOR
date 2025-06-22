package academic.models

import kotlinx.serialization.Serializable

@Serializable
data class Mahasiswa(
    val id: String,
    val nama: String,
    val nim: String,
    val email: String,
    val telepon: String,
    val programStudi: String,
    val semester: Int,
    val kelas: String,
    val password: String
)

@Serializable
data class MahasiswaResponse(
    val id: String,
    val nama: String,
    val nim: String,
    val kelas: String
)

@Serializable
data class MahasiswaCreateRequest(
    val nama: String,
    val nim: String,
    val email: String,
    val telepon: String,
    val programStudi: String,
    val semester: Int,
    val kelas: String,
    val password: String
)

@Serializable
data class MahasiswaUpdateRequest(
    val nama: String? = null,
    val kelas: String? = null,
    val password: String? = null
)
