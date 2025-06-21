package academic.repositories

import academic.db.tables.MahasiswaTable
import academic.models.Mahasiswa
import academic.models.toMahasiswa
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class MahasiswaRepository {
    fun getAll(): List<Mahasiswa> = transaction {
        MahasiswaTable.selectAll().map { it.toMahasiswa() }
    }

    fun getById(id: UUID): Mahasiswa? = transaction {
        MahasiswaTable.select { MahasiswaTable.id eq id }
            .mapNotNull { it.toMahasiswa() }
            .singleOrNull()
    }

    fun create(mhs: Mahasiswa): Mahasiswa = transaction {
        MahasiswaTable.insert {
            it[MahasiswaTable.id] = mhs.id
            it[MahasiswaTable.nama] = mhs.nama
            it[MahasiswaTable.nim] = mhs.nim
            it[MahasiswaTable.email] = mhs.email
            it[MahasiswaTable.telepon] = mhs.telepon
            it[MahasiswaTable.programStudi] = mhs.programStudi
            it[MahasiswaTable.semester] = mhs.semester
        }
        mhs
    }

    fun update(id: UUID, mhs: Mahasiswa): Boolean = transaction {
        MahasiswaTable.update({ MahasiswaTable.id eq id }) {
            it[MahasiswaTable.nama] = mhs.nama
            it[MahasiswaTable.nim] = mhs.nim
            it[MahasiswaTable.email] = mhs.email
            it[MahasiswaTable.telepon] = mhs.telepon
            it[MahasiswaTable.programStudi] = mhs.programStudi
            it[MahasiswaTable.semester] = mhs.semester
        } > 0
    }

    fun delete(id: UUID): Boolean = transaction {
        MahasiswaTable.deleteWhere { MahasiswaTable.id eq id } > 0
    }
}
