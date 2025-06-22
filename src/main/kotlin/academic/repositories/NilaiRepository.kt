package academic.repositories

import academic.db.tables.NilaiTable
import academic.models.Nilai
import academic.models.NilaiUpdateRequest
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class NilaiRepository {
    
    fun findAll(): List<Nilai> = transaction {
        NilaiTable.selectAll().map { row ->
            Nilai(
                id = row[NilaiTable.id],
                mahasiswaId = row[NilaiTable.mahasiswaId],
                mataKuliahKode = row[NilaiTable.mataKuliahKode],
                nilaiAngka = row[NilaiTable.nilaiAngka],
                nilaiHuruf = row[NilaiTable.nilaiHuruf]
            )
        }
    }
    
    fun findByMahasiswaId(mahasiswaId: String): List<Nilai> = transaction {
        NilaiTable.select { NilaiTable.mahasiswaId eq mahasiswaId }.map { row ->
            Nilai(
                id = row[NilaiTable.id],
                mahasiswaId = row[NilaiTable.mahasiswaId],
                mataKuliahKode = row[NilaiTable.mataKuliahKode],
                nilaiAngka = row[NilaiTable.nilaiAngka],
                nilaiHuruf = row[NilaiTable.nilaiHuruf]
            )
        }
    }
    
    fun create(nilai: Nilai): Nilai = transaction {
        NilaiTable.insert {
            it[id] = nilai.id
            it[mahasiswaId] = nilai.mahasiswaId
            it[mataKuliahKode] = nilai.mataKuliahKode
            it[nilaiAngka] = nilai.nilaiAngka
            it[nilaiHuruf] = nilai.nilaiHuruf
        }
        nilai
    }
    
    fun update(id: String, request: NilaiUpdateRequest): Boolean = transaction {
        val updateCount = NilaiTable.update({ NilaiTable.id eq id }) {
            request.nilaiAngka?.let { angka -> it[nilaiAngka] = angka }
            request.nilaiHuruf?.let { huruf -> it[nilaiHuruf] = huruf }
        }
        updateCount > 0
    }
    
    fun delete(id: String): Boolean = transaction {
        NilaiTable.deleteWhere { NilaiTable.id eq id } > 0
    }
}
