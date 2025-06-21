package academic.db.tables

import org.jetbrains.exposed.sql.Table

object MahasiswaMatkulTable : Table("mahasiswa_matkul") {
    val mahasiswaId = uuid("mahasiswa_id")
    val matkulKode = varchar("matkul_kode", 10).references(MataKuliahTable.kode)
    override val primaryKey = PrimaryKey(mahasiswaId, matkulKode)
}
