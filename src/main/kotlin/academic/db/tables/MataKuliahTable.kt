package academic.db.tables

import org.jetbrains.exposed.sql.Table

object MataKuliahTable : Table("matakuliah") {
    val kode = varchar("kode", 20)
    val nama = varchar("nama", 100)
    val dosenId = varchar("dosen_id", 36)
    val sks = integer("sks")
    val semester = integer("semester")
    val deskripsi = text("deskripsi")
    
    override val primaryKey = PrimaryKey(kode)
}
