package academic.models

data class MahasiswaMatkul(
    val mhsId: Int,
    val matkulKode: String
)

data class Bimbingan(
    val dosenId: Int,
    val mhsId: Int
)
