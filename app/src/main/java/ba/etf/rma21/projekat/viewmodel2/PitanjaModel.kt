package ba.etf.rma21.projekat.viewmodel2

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.repositories.PitanjeKvizRepository
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PitanjaModel {
    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun search(kvizId: Int, onSuccess: (kvizId: Int,pitanja: List<Pitanje>) -> Unit,
               onError: () -> Unit) {
        scope.launch {
            val result = PitanjeKvizRepository.getPitanja(kvizId) // ovo je tipa GetPredmetResponse

            when(result) {
                is List<Pitanje> -> onSuccess?.invoke(kvizId,result)
                else-> onError?.invoke()
            }
        }
    }
}