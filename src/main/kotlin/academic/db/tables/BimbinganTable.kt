package academic.db.tables

import org.jetbrains.exposed.sql.Table

object BimbinganTable : Table("bimbingan") {
    val dosenId = integer("dosen_id").references(DosenTable.id)
    val mahasiswaId = integer("mahasiswa_id").references(MahasiswaTable.id)
    override val primaryKey = PrimaryKey(dosenId, mahasiswaId)
}
