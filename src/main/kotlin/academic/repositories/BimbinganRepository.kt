package academic.repositories

import academic.models.Relasi
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class BimbinganRepository {
    fun getAll(): List<Relasi> = transaction {
        emptyList()
    }

    fun getByDosenId(dosenId: UUID): List<Relasi> = transaction {
        emptyList()
    }

    fun create(relasi: Relasi): Relasi = transaction {
        relasi
    }

    fun delete(id: UUID): Boolean = transaction {
        true
    }
}
