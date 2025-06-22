package academic.db.tables

import org.jetbrains.exposed.sql.Table
import java.util.UUID

object UserTable : Table("users") {
    val id = uuid("id").default(UUID.randomUUID())
    val email = varchar("email", 100).uniqueIndex()
    val password = varchar("password", 100)
    val name = varchar("name", 100)
    val role = varchar("role", 20)
    val nim = varchar("nim", 20).nullable()   // ✅ Tambahkan NIM (untuk mahasiswa)
    val nidn = varchar("nidn", 20).nullable() // ✅ Tambahkan NIDN (untuk dosen)

    override val primaryKey = PrimaryKey(id)
}
