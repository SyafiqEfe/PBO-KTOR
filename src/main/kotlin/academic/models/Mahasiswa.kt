package academic.models

import academic.db.tables.MahasiswaTable
import org.jetbrains.exposed.sql.ResultRow
import java.util.*

data class Mahasiswa(
    val id: UUID,
    val nama: String,
    val nim: String,
    val email: String,
    val telepon: String,
    val programStudi: String,
    val semester: Int
)

fun ResultRow.toMahasiswa(): Mahasiswa {
    return Mahasiswa(
        id = this[MahasiswaTable.id],
        nama = this[MahasiswaTable.nama],
        nim = this[MahasiswaTable.nim],
        email = this[MahasiswaTable.email],
        telepon = this[MahasiswaTable.telepon],
        programStudi = this[MahasiswaTable.programStudi],
        semester = this[MahasiswaTable.semester]
    )
}
