package academic.models

import kotlinx.serialization.Serializable

@Serializable
data class KRS(
    val id: String,
    val mahasiswaId: String,
    val mataKuliahKode: String,
    val mahasiswaNama: String? = null,
    val mataKuliahNama: String? = null
)

@Serializable
data class KRSCreateRequest(
    val mahasiswaId: String,
    val mataKuliahKode: String
)
