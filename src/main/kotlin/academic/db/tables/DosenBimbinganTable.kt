package academic.db.tables

import org.jetbrains.exposed.sql.Table

object DosenBimbinganTable : Table("dosen_bimbingan") {
    val dosenId = uuid("dosen_id")
    val mahasiswaId = uuid("mahasiswa_id")
    override val primaryKey = PrimaryKey(dosenId, mahasiswaId)
}
