package academic.db.tables

import org.jetbrains.exposed.sql.Table

object NilaiTable : Table("nilai") {
    val id = varchar("id", 36)
    val mahasiswaId = varchar("mahasiswa_id", 36)
    val mataKuliahKode = varchar("matakuliah_kode", 10)
    val nilaiAngka = double("nilai_angka")
    val nilaiHuruf = varchar("nilai_huruf", 2)
    
    override val primaryKey = PrimaryKey(id)
}
