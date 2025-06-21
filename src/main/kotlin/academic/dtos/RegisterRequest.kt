package academic.dtos

import academic.models.UserRole
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val email: String,
    val password: String,
    val name: String,
    val role: UserRole
)
