package academic.db.tables

import org.jetbrains.exposed.sql.Table

object MahasiswaTable : Table("mahasiswa") {
    val id = varchar("id", 36)
    val nama = varchar("nama", 100)
    val nim = varchar("nim", 20).uniqueIndex()
    val email = varchar("email", 100)
    val telepon = varchar("telepon", 20)
    val programStudi = varchar("program_studi", 100)
    val semester = integer("semester")
    val kelas = varchar("kelas", 10)
    val password = varchar("password", 255)
    
    override val primaryKey = PrimaryKey(id)
}
