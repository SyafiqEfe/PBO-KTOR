package academic.dtos

import kotlinx.serialization.Serializable

@Serializable
data class MataKuliahCreateRequest(
    val kode: String,
    val nama: String,
    val sks: Int,
    val semester: Int,
    val deskripsi: String = ""
)
