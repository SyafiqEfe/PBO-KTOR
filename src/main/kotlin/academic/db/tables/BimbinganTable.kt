package academic.db.tables

import org.jetbrains.exposed.sql.Table

object BimbinganTable : Table("bimbingan") {
    val dosenId = uuid("dosen_id")
    val mahasiswaId = uuid("mahasiswa_id")
    override val primaryKey = PrimaryKey(dosenId, mahasiswaId)
}
