package academic.repositories

import academic.db.tables.DosenTable
import academic.models.Dosen
import academic.models.toDosen
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class DosenRepository {
    fun getAll(): List<Dosen> = transaction {
        DosenTable.selectAll().map { it.toDosen() }
    }

    fun getById(id: UUID): Dosen? = transaction {
        DosenTable.select { DosenTable.id eq id }
            .mapNotNull { it.toDosen() }
            .singleOrNull()
    }

    fun create(dosen: Dosen): Dosen = transaction {
        DosenTable.insert {
            it[DosenTable.id] = dosen.id
            it[DosenTable.nama] = dosen.nama
            it[DosenTable.nidn] = dosen.nidn
            it[DosenTable.email] = dosen.email
            it[DosenTable.telepon] = dosen.telepon
            it[DosenTable.departemen] = dosen.departemen
        }
        dosen
    }

    fun update(id: UUID, dosen: Dosen): Boolean = transaction {
        DosenTable.update({ DosenTable.id eq id }) {
            it[DosenTable.nama] = dosen.nama
            it[DosenTable.nidn] = dosen.nidn
            it[DosenTable.email] = dosen.email
            it[DosenTable.telepon] = dosen.telepon
            it[DosenTable.departemen] = dosen.departemen
        } > 0
    }

    fun delete(id: UUID): Boolean = transaction {
        DosenTable.deleteWhere { DosenTable.id eq id } > 0
    }
}
