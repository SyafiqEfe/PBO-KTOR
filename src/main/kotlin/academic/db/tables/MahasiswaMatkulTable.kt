package academic.db.tables

import org.jetbrains.exposed.sql.Table

object MahasiswaMatkulTable : Table("mahasiswa_matkul") {
    val mahasiswaId = integer("mahasiswa_id").references(MahasiswaTable.id)
    val matkulKode = varchar("matkul_kode", 10).references(MataKuliahTable.kode)
    override val primaryKey = PrimaryKey(mahasiswaId, matkulKode)
}
