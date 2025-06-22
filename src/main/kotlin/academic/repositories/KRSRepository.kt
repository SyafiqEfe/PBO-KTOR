package academic.repositories

import academic.db.tables.KRSTable
import academic.models.KRS
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class KRSRepository {
    
    fun findAll(): List<KRS> = transaction {
        KRSTable.selectAll().map { row ->
            KRS(
                id = row[KRSTable.id],
                mahasiswaId = row[KRSTable.mahasiswaId],
                mataKuliahKode = row[KRSTable.mataKuliahKode]
            )
        }
    }
    
    fun findByMahasiswaId(mahasiswaId: String): List<KRS> = transaction {
        KRSTable.select { KRSTable.mahasiswaId eq mahasiswaId }.map { row ->
            KRS(
                id = row[KRSTable.id],
                mahasiswaId = row[KRSTable.mahasiswaId],
                mataKuliahKode = row[KRSTable.mataKuliahKode]
            )
        }
    }
    
    fun create(krs: KRS): KRS = transaction {
        KRSTable.insert {
            it[id] = krs.id
            it[mahasiswaId] = krs.mahasiswaId
            it[mataKuliahKode] = krs.mataKuliahKode
        }
        krs
    }
    
    fun delete(id: String): Boolean = transaction {
        KRSTable.deleteWhere { KRSTable.id eq id } > 0
    }
}
