package academic.repositories

import academic.db.tables.UserTable
import academic.models.User
import academic.models.toUser
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class UserRepository {

    fun getAll(): List<User> = transaction {
        UserTable.selectAll().map { it.toUser() }
    }

    fun findById(id: UUID): User? = transaction {
        UserTable.select { UserTable.id eq id }
            .mapNotNull { it.toUser() }
            .singleOrNull()
    }

    fun findByEmail(email: String): User? = transaction {
        UserTable.select { UserTable.email eq email }
            .mapNotNull { it.toUser() }
            .singleOrNull()
    }

    fun findByNim(nim: String): User? = transaction {
        UserTable.select { UserTable.nim eq nim }
            .mapNotNull { it.toUser() }
            .singleOrNull()
    }

    fun findByNidn(nidn: String): User? = transaction {
        UserTable.select { UserTable.nidn eq nidn }
            .mapNotNull { it.toUser() }
            .singleOrNull()
    }

    fun create(user: User): User = transaction {
        UserTable.insert {
            it[id] = user.id
            it[email] = user.email
            it[password] = user.password
            it[name] = user.name
            it[role] = user.role
            it[nim] = user.nim
            it[nidn] = user.nidn
        }
        user
    }

    fun delete(id: UUID): Boolean = transaction {
        UserTable.deleteWhere { UserTable.id eq id } > 0
    }
}
