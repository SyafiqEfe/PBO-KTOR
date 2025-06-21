package academic.db.tables

import org.jetbrains.exposed.sql.Table

object MahasiswaTable : Table("mahasiswa") {
    val id = uuid("id").autoGenerate()
    val nama = varchar("nama", 100)
    val nim = varchar("nim", 20).uniqueIndex()
    val email = varchar("email", 100)
    val telepon = varchar("telepon", 20)
    val programStudi = varchar("program_studi", 100)
    val semester = integer("semester")
    override val primaryKey = PrimaryKey(id)
}
