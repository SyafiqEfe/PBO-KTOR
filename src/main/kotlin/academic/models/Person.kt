package academic.models

// Remove @Serializable annotation from base class
open class Person(
    open val id: String,
    open val nama: String,
    open val email: String,
    open val telepon: String
) {
    open fun displayInfo(): String {
        return """
            ID: $id
            Nama: $nama
            Email: $email
            Telepon: $telepon
        """.trimIndent()
    }
}
