package academic.repositories

import academic.db.tables.MataKuliahTable
import academic.db.tables.DosenTable
import academic.models.MataKuliah
import academic.models.MataKuliahCreateRequest
import academic.models.MataKuliahUpdateRequest
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class MataKuliahRepository {
    
    fun findAll(): List<MataKuliah> = transaction {
        (MataKuliahTable innerJoin DosenTable)
            .selectAll()
            .map { row ->
                MataKuliah(
                    kode = row[MataKuliahTable.kode],
                    nama = row[MataKuliahTable.nama],
                    dosenId = row[MataKuliahTable.dosenId],
                    dosenNama = row[DosenTable.nama],
                    sks = row[MataKuliahTable.sks],
                    semester = row[MataKuliahTable.semester],
                    deskripsi = row[MataKuliahTable.deskripsi]
                )
            }
    }
    
    fun findByKode(kode: String): MataKuliah? = transaction {
        (MataKuliahTable innerJoin DosenTable)
            .select { MataKuliahTable.kode eq kode }
            .map { row ->
                MataKuliah(
                    kode = row[MataKuliahTable.kode],
                    nama = row[MataKuliahTable.nama],
                    dosenId = row[MataKuliahTable.dosenId],
                    dosenNama = row[DosenTable.nama],
                    sks = row[MataKuliahTable.sks],
                    semester = row[MataKuliahTable.semester],
                    deskripsi = row[MataKuliahTable.deskripsi]
                )
            }.singleOrNull()
    }
    
    fun create(request: MataKuliahCreateRequest): MataKuliah = transaction {
        MataKuliahTable.insert {
            it[kode] = request.kode
            it[nama] = request.nama
            it[dosenId] = request.dosenId
            it[sks] = request.sks
            it[semester] = request.semester
            it[deskripsi] = request.deskripsi
        }
        
        findByKode(request.kode)!!
    }
    
    fun update(kode: String, request: MataKuliahUpdateRequest): Boolean = transaction {
        val updateCount = MataKuliahTable.update({ MataKuliahTable.kode eq kode }) {
            request.nama?.let { nama -> it[MataKuliahTable.nama] = nama }
            request.dosenId?.let { dosenId -> it[MataKuliahTable.dosenId] = dosenId }
            request.sks?.let { sks -> it[MataKuliahTable.sks] = sks }
            request.semester?.let { semester -> it[MataKuliahTable.semester] = semester }
            request.deskripsi?.let { deskripsi -> it[MataKuliahTable.deskripsi] = deskripsi }
        }
        updateCount > 0
    }
    
    fun delete(kode: String): Boolean = transaction {
        val deleteCount = MataKuliahTable.deleteWhere { MataKuliahTable.kode eq kode }
        deleteCount > 0
    }
}
