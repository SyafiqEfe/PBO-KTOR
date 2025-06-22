package academic.repositories

import academic.db.tables.DosenTable
import academic.models.Dosen
import academic.models.DosenCreateRequest
import academic.models.DosenUpdateRequest
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.mindrot.jbcrypt.BCrypt
import java.util.*

class DosenRepository {
    
    fun findAll(): List<Dosen> = transaction {
        DosenTable.selectAll().map { row ->
            Dosen(
                id = row[DosenTable.id],
                nama = row[DosenTable.nama],
                nidn = row[DosenTable.nidn],
                email = row[DosenTable.email],
                telepon = row[DosenTable.telepon],
                departemen = row[DosenTable.departemen],
                password = row[DosenTable.password]
            )
        }
    }
    
    fun findById(id: String): Dosen? = transaction {
        DosenTable.select { DosenTable.id eq id }
            .map { row ->
                Dosen(
                    id = row[DosenTable.id],
                    nama = row[DosenTable.nama],
                    nidn = row[DosenTable.nidn],
                    email = row[DosenTable.email],
                    telepon = row[DosenTable.telepon],
                    departemen = row[DosenTable.departemen],
                    password = row[DosenTable.password]
                )
            }.singleOrNull()
    }
    
    fun findByNidn(nidn: String): Dosen? = transaction {
        DosenTable.select { DosenTable.nidn eq nidn }
            .map { row ->
                Dosen(
                    id = row[DosenTable.id],
                    nama = row[DosenTable.nama],
                    nidn = row[DosenTable.nidn],
                    email = row[DosenTable.email],
                    telepon = row[DosenTable.telepon],
                    departemen = row[DosenTable.departemen],
                    password = row[DosenTable.password]
                )
            }.singleOrNull()
    }
    
    fun create(request: DosenCreateRequest): Dosen = transaction {
        val id = UUID.randomUUID().toString()
        val hashedPassword = BCrypt.hashpw(request.password, BCrypt.gensalt())
        
        DosenTable.insert {
            it[DosenTable.id] = id
            it[nama] = request.nama
            it[nidn] = request.nidn
            it[email] = request.email
            it[telepon] = request.telepon
            it[departemen] = request.departemen
            it[password] = hashedPassword
        }
        
        Dosen(
            id = id,
            nama = request.nama,
            nidn = request.nidn,
            email = request.email,
            telepon = request.telepon,
            departemen = request.departemen,
            password = hashedPassword
        )
    }
    
    fun update(id: String, request: DosenUpdateRequest): Boolean = transaction {
        val updateCount = DosenTable.update({ DosenTable.id eq id }) {
            request.nama?.let { nama -> it[DosenTable.nama] = nama }
            request.password?.let { password -> 
                it[DosenTable.password] = BCrypt.hashpw(password, BCrypt.gensalt())
            }
        }
        updateCount > 0
    }
    
    fun delete(id: String): Boolean = transaction {
        val deleteCount = DosenTable.deleteWhere { DosenTable.id eq id }
        deleteCount > 0
    }
    
    fun authenticate(nidn: String, password: String): Dosen? {
        val dosen = findByNidn(nidn)
        return if (dosen != null && BCrypt.checkpw(password, dosen.password)) {
            dosen
        } else {
            null
        }
    }
}
