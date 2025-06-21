package academic.db.tables

import org.jetbrains.exposed.sql.Table

object DosenTable : Table("dosen") {
    val id = integer("id").autoIncrement()
    val nama = varchar("nama", 100)
    val nip = varchar("nip", 20).uniqueIndex()
    override val primaryKey = PrimaryKey(id)
}
