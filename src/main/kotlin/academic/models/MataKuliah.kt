package academic.models

import academic.db.tables.MataKuliahTable
import org.jetbrains.exposed.sql.ResultRow

data class MataKuliah(
    val kode: String,
    val nama: String,
    val sks: Int,
    val semester: Int,
    val deskripsi: String? = null
)

fun ResultRow.toMataKuliah(): MataKuliah {
    return MataKuliah(
        kode = this[MataKuliahTable.kode],
        nama = this[MataKuliahTable.nama],
        sks = this[MataKuliahTable.sks],
        semester = this[MataKuliahTable.semester],
        deskripsi = this[MataKuliahTable.deskripsi]
    )
}
