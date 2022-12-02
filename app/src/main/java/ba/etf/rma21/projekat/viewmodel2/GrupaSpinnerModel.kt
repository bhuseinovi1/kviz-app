package ba.etf.rma21.projekat.viewmodel2

import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository
import ba.etf.rma21.projekat.data.models.Grupa
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GrupaSpinnerModel {
    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun search(predmetId: Int,onSuccess: (predmeti: List<Grupa>) -> Unit,
               onError: () -> Unit) {
        scope.launch {
            val result = PredmetIGrupaRepository.getGrupeZaPredmet(predmetId) // ovo je tipa GetPredmetResponse

            when(result) {
                is List<Grupa> -> onSuccess?.invoke(result)
                else-> onError?.invoke()
            }
        }
    }

    fun searchGroupsByKvizId(kvizId: Int,onSuccess: (predmeti: List<Grupa>) -> Unit,
               onError: () -> Unit) {
        scope.launch {
            val result = PredmetIGrupaRepository.getGrupeZaKviz(kvizId) // ovo je tipa GetPredmetResponse

            when(result) {
                is List<Grupa> -> onSuccess?.invoke(result)
                else-> onError?.invoke()
            }
        }
    }
}