package academic.db.tables

import org.jetbrains.exposed.sql.Table

object DosenTable : Table("dosen") {
    val id = uuid("id").autoGenerate()
    val nama = varchar("nama", 100)
    val nidn = varchar("nidn", 20).uniqueIndex()
    val email = varchar("email", 100)
    val telepon = varchar("telepon", 20)
    val departemen = varchar("departemen", 100)
    override val primaryKey = PrimaryKey(id)
}
