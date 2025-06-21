package academic.repositories

import academic.db.tables.MataKuliahTable
import academic.models.MataKuliah
import academic.models.toMataKuliah
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class MataKuliahRepository {
    fun getAll(): List<MataKuliah> = transaction {
        MataKuliahTable.selectAll().map { it.toMataKuliah() }
    }

    fun getByKode(kode: String): MataKuliah? = transaction {
        MataKuliahTable.select { MataKuliahTable.kode eq kode }
            .mapNotNull { it.toMataKuliah() }
            .singleOrNull()
    }

    fun create(matkul: MataKuliah): MataKuliah = transaction {
        MataKuliahTable.insert {
            it[MataKuliahTable.kode] = matkul.kode
            it[MataKuliahTable.nama] = matkul.nama
            it[MataKuliahTable.sks] = matkul.sks
            it[MataKuliahTable.semester] = matkul.semester
            it[MataKuliahTable.deskripsi] = matkul.deskripsi
        }
        matkul
    }

    fun update(kode: String, matkul: MataKuliah): Boolean = transaction {
        MataKuliahTable.update({ MataKuliahTable.kode eq kode }) {
            it[MataKuliahTable.nama] = matkul.nama
            it[MataKuliahTable.sks] = matkul.sks
            it[MataKuliahTable.semester] = matkul.semester
            it[MataKuliahTable.deskripsi] = matkul.deskripsi
        } > 0
    }

    fun delete(kode: String): Boolean = transaction {
        MataKuliahTable.deleteWhere { MataKuliahTable.kode eq kode } > 0
    }
}
