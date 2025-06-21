package academic.interfaces

import academic.models.MataKuliah
import academic.exceptions.AcademicException

interface Registrasi {
    fun ambilMataKuliah(mataKuliah: MataKuliah)
    fun dropMataKuliah(kodeMk: String)
    fun totalSKS(): Int
}