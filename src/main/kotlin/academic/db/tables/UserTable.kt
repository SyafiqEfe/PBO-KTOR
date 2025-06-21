package academic.db.tables

import org.jetbrains.exposed.sql.Table

object UserTable : Table("users") {
    val id = integer("id").autoIncrement()
    val username = varchar("username", 50).uniqueIndex()
    val password = varchar("password", 100)
    val role = varchar("role", 20)
    override val primaryKey = PrimaryKey(id)
}
