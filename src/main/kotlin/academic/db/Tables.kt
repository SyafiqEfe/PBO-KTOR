package academic.database

import org.jetbrains.exposed.sql.Table

object MahasiswaTable : Table("mahasiswa") {
    val id = varchar("id", 36)
    val nama = varchar("nama", 100)
    val nim = varchar("nim", 20).uniqueIndex()
    val kelas = varchar("kelas", 10)
    val password = varchar("password", 100)
    override val primaryKey = PrimaryKey(id)
}

object DosenTable : Table("dosen") {
    val id = varchar("id", 36)
    val nama = varchar("nama", 100)
    val nidn = varchar("nidn", 20).uniqueIndex()
    val password = varchar("password", 100)
    override val primaryKey = PrimaryKey(id)
}

object MataKuliahTable : Table("matakuliah") {
    val id = varchar("id", 36)
    val nama = varchar("nama", 100)
    val dosenId = varchar("dosen_id", 36).references(DosenTable.id)
    val sks = integer("sks")
    val jamKuliah = varchar("jam_kuliah", 20)
    val kelas = varchar("kelas", 10)
    override val primaryKey = PrimaryKey(id)
}

object KRSTable : Table("krs") {
    val id = varchar("id", 36)
    val mahasiswaId = varchar("mahasiswa_id", 36).references(MahasiswaTable.id)
    val matakuliahId = varchar("matakuliah_id", 36).references(MataKuliahTable.id)
    override val primaryKey = PrimaryKey(id)
    
    init {
        uniqueIndex(mahasiswaId, matakuliahId)
    }
}

object NilaiTable : Table("nilai") {
    val id = varchar("id", 36)
    val mahasiswaId = varchar("mahasiswa_id", 36).references(MahasiswaTable.id)
    val matakuliahId = varchar("matakuliah_id", 36).references(MataKuliahTable.id)
    val nilaiAngka = double("nilai_angka")
    val nilaiHuruf = varchar("nilai_huruf", 1)
    override val primaryKey = PrimaryKey(id)
    
    init {
        uniqueIndex(mahasiswaId, matakuliahId)
    }
}
