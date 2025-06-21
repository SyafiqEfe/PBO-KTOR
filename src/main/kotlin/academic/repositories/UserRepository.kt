package academic.repositories

import academic.models.*
import academic.exceptions.AcademicException
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class UserRepository {
    private val db = ConcurrentHashMap<String, User>()

    fun create(email: String, password: String, role: UserRole, name: String): User {
        if (db.values.any { it.email == email }) {
            throw AcademicException("Email already registered")
        }
        
        val user = User(
            id = UUID.randomUUID().toString(),
            email = email,
            password = password, // Note: In production, hash the password
            role = role,
            name = name
        )
        
        db[user.id] = user
        return user
    }

    fun findByEmail(email: String): User? {
        return db.values.find { it.email == email }
    }

    fun findById(id: String): User? {
        return db[id]
    }
}
