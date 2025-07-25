package academic.interfaces

import academic.models.MataKuliah

interface Registrasi {
    fun ambilMataKuliah(mataKuliah: MataKuliah)
    fun dropMataKuliah(kodeMk: String)
    fun totalSKS(): Int
}
