package academic.db.tables

import org.jetbrains.exposed.sql.Table

object MahasiswaTable : Table("mahasiswa") {
    val id = integer("id").autoIncrement()
    val nama = varchar("nama", 100)
    val nim = varchar("nim", 20).uniqueIndex()
    override val primaryKey = PrimaryKey(id)
}
