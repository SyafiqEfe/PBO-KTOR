package academic.db.tables

import org.jetbrains.exposed.sql.Table

object KRSTable : Table("krs") {
    val id = varchar("id", 36)
    val mahasiswaId = varchar("mahasiswa_id", 36)
    val mataKuliahKode = varchar("matakuliah_kode", 10)
    
    override val primaryKey = PrimaryKey(id)
}
