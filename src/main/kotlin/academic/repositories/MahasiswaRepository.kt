package academic.repositories

import academic.db.tables.MahasiswaTable
import academic.models.Mahasiswa
import academic.models.MahasiswaCreateRequest
import academic.models.MahasiswaUpdateRequest
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.mindrot.jbcrypt.BCrypt
import java.util.*

class MahasiswaRepository {
    
    fun findAll(): List<Mahasiswa> = transaction {
        MahasiswaTable.selectAll().map { row ->
            Mahasiswa(
                id = row[MahasiswaTable.id],
                nama = row[MahasiswaTable.nama],
                nim = row[MahasiswaTable.nim],
                email = row[MahasiswaTable.email],
                telepon = row[MahasiswaTable.telepon],
                programStudi = row[MahasiswaTable.programStudi],
                semester = row[MahasiswaTable.semester],
                kelas = row[MahasiswaTable.kelas],
                password = row[MahasiswaTable.password]
            )
        }
    }
    
    fun findById(id: String): Mahasiswa? = transaction {
        MahasiswaTable.select { MahasiswaTable.id eq id }
            .map { row ->
                Mahasiswa(
                    id = row[MahasiswaTable.id],
                    nama = row[MahasiswaTable.nama],
                    nim = row[MahasiswaTable.nim],
                    email = row[MahasiswaTable.email],
                    telepon = row[MahasiswaTable.telepon],
                    programStudi = row[MahasiswaTable.programStudi],
                    semester = row[MahasiswaTable.semester],
                    kelas = row[MahasiswaTable.kelas],
                    password = row[MahasiswaTable.password]
                )
            }.singleOrNull()
    }
    
    fun findByNim(nim: String): Mahasiswa? = transaction {
        MahasiswaTable.select { MahasiswaTable.nim eq nim }
            .map { row ->
                Mahasiswa(
                    id = row[MahasiswaTable.id],
                    nama = row[MahasiswaTable.nama],
                    nim = row[MahasiswaTable.nim],
                    email = row[MahasiswaTable.email],
                    telepon = row[MahasiswaTable.telepon],
                    programStudi = row[MahasiswaTable.programStudi],
                    semester = row[MahasiswaTable.semester],
                    kelas = row[MahasiswaTable.kelas],
                    password = row[MahasiswaTable.password]
                )
            }.singleOrNull()
    }
    
    fun create(request: MahasiswaCreateRequest): Mahasiswa = transaction {
        val id = UUID.randomUUID().toString()
        val hashedPassword = BCrypt.hashpw(request.password, BCrypt.gensalt())
        
        MahasiswaTable.insert {
            it[MahasiswaTable.id] = id
            it[nama] = request.nama
            it[nim] = request.nim
            it[email] = request.email
            it[telepon] = request.telepon
            it[programStudi] = request.programStudi
            it[semester] = request.semester
            it[kelas] = request.kelas
            it[password] = hashedPassword
        }
        
        Mahasiswa(
            id = id,
            nama = request.nama,
            nim = request.nim,
            email = request.email,
            telepon = request.telepon,
            programStudi = request.programStudi,
            semester = request.semester,
            kelas = request.kelas,
            password = hashedPassword
        )
    }
    
    fun update(id: String, request: MahasiswaUpdateRequest): Boolean = transaction {
        val updateCount = MahasiswaTable.update({ MahasiswaTable.id eq id }) {
            request.nama?.let { nama -> it[MahasiswaTable.nama] = nama }
            request.kelas?.let { kelas -> it[MahasiswaTable.kelas] = kelas }
            request.password?.let { password -> 
                it[MahasiswaTable.password] = BCrypt.hashpw(password, BCrypt.gensalt())
            }
        }
        updateCount > 0
    }
    
    fun delete(id: String): Boolean = transaction {
        val deleteCount = MahasiswaTable.deleteWhere { MahasiswaTable.id eq id }
        deleteCount > 0
    }
    
    fun authenticate(nim: String, password: String): Mahasiswa? {
        val mahasiswa = findByNim(nim)
        return if (mahasiswa != null && BCrypt.checkpw(password, mahasiswa.password)) {
            mahasiswa
        } else {
            null
        }
    }
}
