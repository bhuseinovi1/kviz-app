package ba.etf.rma21.projekat.viewmodel2

import android.content.Context
import android.widget.Toast
import ba.etf.rma21.projekat.KvizListAdapter
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository
import ba.etf.rma21.projekat.data.models.Predmet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PredmetSpinnerModel() {

    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun search(onSuccess: (predmeti: List<Predmet>) -> Unit,
               onError: () -> Unit) {
        scope.launch {
            val result = PredmetIGrupaRepository.getPredmeti() // ovo je tipa GetPredmetResponse

            when(result) {
                is List<Predmet> -> onSuccess?.invoke(result)
                else-> onError?.invoke()
            }
        }
    }

    fun searchPredmetByKvizId(context: Context,holder: KvizListAdapter.KvizViewHolder,position: Int,kvizId: Int,onSuccess: (holder: KvizListAdapter.KvizViewHolder,position: Int,predmeti: List<Predmet>) -> Unit,
                              onError: (holder: KvizListAdapter.KvizViewHolder) -> Unit) {
        scope.launch {
            val result = PredmetIGrupaRepository.getPredmetZaKviz(kvizId) // ovo je tipa GetPredmetResponse

            /*
            var toast: Toast = Toast.makeText(context,result.toString(),Toast.LENGTH_LONG)
            toast.show()

             */
            when(result) {
                is List<Predmet> -> onSuccess?.invoke(holder,position, result)
                else-> onError?.invoke(holder)
            }
        }
    }

    /*
    fun search(onSuccess: (predmeti: List<Predmet>) -> Unit,
               onError: () -> Unit) {
        scope.launch {
            val result = PredmetRepository.getAll() // ovo je tipa GetPredmetResponse

            when(result) {
                is List<Predmet> -> onSuccess?.invoke(result)
                else-> onError?.invoke()
            }
        }
    }

     */
}