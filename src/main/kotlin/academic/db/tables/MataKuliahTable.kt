package academic.db.tables

import org.jetbrains.exposed.sql.Table

object MataKuliahTable : Table("matakuliah") {
    val kode = varchar("kode", 10)
    val nama = varchar("nama", 100)
    val sks = integer("sks")
    val semester = integer("semester")
    val deskripsi = text("deskripsi").nullable()
    override val primaryKey = PrimaryKey(kode)
}
