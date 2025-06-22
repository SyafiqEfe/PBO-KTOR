package academic.models

import kotlinx.serialization.Serializable

@Serializable
data class Nilai(
    val id: String,
    val mahasiswaId: String,
    val mataKuliahKode: String,
    val nilaiAngka: Double,
    val nilaiHuruf: String,
    val mahasiswaNama: String? = null,
    val mataKuliahNama: String? = null
)

@Serializable
data class NilaiCreateRequest(
    val mahasiswaId: String,
    val mataKuliahKode: String,
    val nilaiAngka: Double,
    val nilaiHuruf: String
)

@Serializable
data class NilaiUpdateRequest(
    val nilaiAngka: Double? = null,
    val nilaiHuruf: String? = null
)
