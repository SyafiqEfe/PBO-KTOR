package academic.repositories

import academic.db.tables.MahasiswaMatkulTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class MahasiswaMatkulRepository {
    fun ambilMatkul(mahasiswaId: UUID, matkulKode: String) = transaction {
        MahasiswaMatkulTable.insertIgnore {
            it[MahasiswaMatkulTable.mahasiswaId] = mahasiswaId
            it[MahasiswaMatkulTable.matkulKode] = matkulKode
        }
    }

    fun dropMatkul(mahasiswaId: UUID, matkulKode: String) = transaction {
        MahasiswaMatkulTable.deleteWhere {
            (MahasiswaMatkulTable.mahasiswaId eq mahasiswaId) and 
            (MahasiswaMatkulTable.matkulKode eq matkulKode)
        }
    }

    fun getMatkulByMahasiswa(mahasiswaId: UUID): List<String> = transaction {
        MahasiswaMatkulTable.select { MahasiswaMatkulTable.mahasiswaId eq mahasiswaId }
            .map { it[MahasiswaMatkulTable.matkulKode] }
    }
}
