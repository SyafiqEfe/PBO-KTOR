package academic.models

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Relasi(
    val id: String = UUID.randomUUID().toString(),
    val mahasiswaId: String,
    val matkulId: String? = null,
    val dosenId: String? = null
)
