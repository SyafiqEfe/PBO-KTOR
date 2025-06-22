package academic.models

import academic.db.tables.UserTable
import org.jetbrains.exposed.sql.ResultRow
import java.util.*

data class User(
    val id: UUID,
    val email: String,
    val name: String,
    val role: String,
    val password: String,
    val nim: String? = null,   // ✅ Tambah properti nim
    val nidn: String? = null   // ✅ Tambah properti nidn
)

fun ResultRow.toUser(): User {
    return User(
        id = this[UserTable.id],
        email = this[UserTable.email],
        name = this[UserTable.name],
        role = this[UserTable.role],
        password = this[UserTable.password],
        nim = this[UserTable.nim],   // ✅ Ambil nim dari table
        nidn = this[UserTable.nidn]  // ✅ Ambil nidn dari table
    )
}
