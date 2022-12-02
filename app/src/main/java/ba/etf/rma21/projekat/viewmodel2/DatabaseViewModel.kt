package ba.etf.rma21.projekat.viewmodel2

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.repositories.DBRepository
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DatabaseViewModel {
    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun biloPromjene(onSuccess: (biloPromjene: Boolean) -> Unit,
               onError: () -> Unit) {
        scope.launch {
            val result = DBRepository.updateNow() // ovo je tipa GetPredmetResponse

            when(result) {
                is Boolean -> onSuccess?.invoke(result)
                //is String -> onSuccess?.invoke(true)
                else-> onError?.invoke()
            }
        }
    }
}