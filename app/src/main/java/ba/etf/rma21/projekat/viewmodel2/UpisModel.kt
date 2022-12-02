package ba.etf.rma21.projekat.viewmodel2

import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UpisModel {

    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun upisi(grupaId: Int,onSuccess: (message:String) -> Unit,
               onError: (message:String) -> Unit) {
        scope.launch {
            val result = PredmetIGrupaRepository.upisiUGrupu(grupaId)

            if (!result)
                onError?.invoke("Greška u zahtjevu")
            else
                onSuccess?.invoke("Uspjeh")
        }
    }
}