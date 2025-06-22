package academic.db.tables

import org.jetbrains.exposed.sql.Table

object MahasiswaMatkulTable : Table("mahasiswa_matkul") {
    val id = varchar("id", 36) // Diisi manual atau generate UUID dari kode Kotlin
    val mahasiswaId = varchar("mahasiswa_id", 36)
    val matkulKode = varchar("matkul_kode", 10)

    override val primaryKey = PrimaryKey(id)
}
