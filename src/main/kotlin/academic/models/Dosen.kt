package academic.models

import academic.db.tables.DosenTable
import org.jetbrains.exposed.sql.ResultRow
import java.util.*

data class Dosen(
    val id: UUID,
    val nama: String,
    val nidn: String,
    val email: String,
    val telepon: String,
    val departemen: String
)

fun ResultRow.toDosen(): Dosen {
    return Dosen(
        id = this[DosenTable.id],
        nama = this[DosenTable.nama],
        nidn = this[DosenTable.nidn],
        email = this[DosenTable.email],
        telepon = this[DosenTable.telepon],
        departemen = this[DosenTable.departemen]
    )
}
