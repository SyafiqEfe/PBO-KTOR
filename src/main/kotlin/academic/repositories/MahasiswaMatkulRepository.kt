package academic.repositories

import academic.db.tables.MahasiswaMatkulTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class MahasiswaMatkulRepository {
    
    fun ambilMatkul(mahasiswaId: String, matkulKode: String) {
        transaction {
            MahasiswaMatkulTable.insert {
                it[this.mahasiswaId] = mahasiswaId
                it[this.matkulKode] = matkulKode
            }
        }
    }
    
    fun dropMatkul(mahasiswaId: String, matkulKode: String) {
        transaction {
            MahasiswaMatkulTable.deleteWhere {
                (MahasiswaMatkulTable.mahasiswaId eq mahasiswaId) and 
                (MahasiswaMatkulTable.matkulKode eq matkulKode)
            }
        }
    }
    
    fun getMatkulByMahasiswa(mahasiswaId: String): List<String> {
        return transaction {
            MahasiswaMatkulTable.select { MahasiswaMatkulTable.mahasiswaId eq mahasiswaId }
                .map { it[MahasiswaMatkulTable.matkulKode] }
        }
    }
}
