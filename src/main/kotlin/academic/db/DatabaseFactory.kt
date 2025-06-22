package academic.db

import academic.db.tables.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        val database = Database.connect(
            url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;",
            driver = "org.h2.Driver"
        )
        
        transaction(database) {
            SchemaUtils.create(
                MahasiswaTable,
                DosenTable,
                MataKuliahTable,
                KRSTable,
                NilaiTable,
                UserTable
            )
        }
    }
}
