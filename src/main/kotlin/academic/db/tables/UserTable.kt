package academic.db.tables

import org.jetbrains.exposed.sql.Table

object UserTable : Table("users") {
    val id = uuid("id").autoGenerate()
    val email = varchar("email", 100).uniqueIndex()
    val password = varchar("password", 100)
    val name = varchar("name", 100)
    val role = varchar("role", 20)
    override val primaryKey = PrimaryKey(id)
}
