package academic.models

import kotlinx.serialization.Serializable

@Serializable
data class MataKuliah(
    val kode: String,
    val nama: String,
    val dosenId: String,
    val dosenNama: String? = null,
    val sks: Int,
    val semester: Int,
    val deskripsi: String
)

@Serializable
data class MataKuliahCreateRequest(
    val kode: String,
    val nama: String,
    val dosenId: String,
    val sks: Int,
    val semester: Int,
    val deskripsi: String
)

@Serializable
data class MataKuliahUpdateRequest(
    val nama: String? = null,
    val dosenId: String? = null,
    val sks: Int? = null,
    val semester: Int? = null,
    val deskripsi: String? = null
)
