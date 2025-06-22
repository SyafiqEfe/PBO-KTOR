package academic.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val identifier: String, // NIM untuk mahasiswa, NIDN untuk dosen
    val password: String,
    val role: String // "mahasiswa" atau "dosen"
)

@Serializable
data class LoginResponse(
    val token: String,
    val user: UserInfo,
    val role: String
)

@Serializable
data class UserInfo(
    val id: String,
    val nama: String,
    val identifier: String // NIM atau NIDN
)
