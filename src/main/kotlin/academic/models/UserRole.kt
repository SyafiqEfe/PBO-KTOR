package academic.models

import kotlinx.serialization.Serializable

@Serializable
enum class UserRole {
    ADMIN, DOSEN, MAHASISWA
}
