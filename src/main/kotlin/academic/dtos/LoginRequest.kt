package academic.dtos

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val role: String,
    val identifier: String,
    val password: String
)

