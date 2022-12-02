package ba.etf.rma21.projekat.viewmodel2

import ba.etf.rma21.projekat.data.AppDatabase
import ba.etf.rma21.projekat.data.repositories.KvizRepository
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.repositories.OdgovorRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class KvizListViewModel {
    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun search(flag: Int,onSuccess: (kvizovi: List<Kviz>) -> Unit,
               onError: () -> Unit) {
        if(flag == 1) {
            scope.launch {
                val result = KvizRepository.getAll() // ovo je tipa GetPredmetResponse

                when (result) {
                    is List<Kviz> -> onSuccess?.invoke(result)
                    else -> onError?.invoke()
                }
            }
        }
        else if(flag == 3) {
            scope.launch {
                val result = KvizRepository.getUpisani() // ovo je tipa GetPredmetResponse

                when (result) {
                    is List<Kviz> -> onSuccess?.invoke(result.filter { it-> SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(it.datumPocetka).after(Calendar.getInstance().time) })
                    else -> onError?.invoke()
                }
            }
        }
        else if(flag == 5) {
            scope.launch {
                val result = KvizRepository.getUpisani()// ovo je tipa GetPredmetResponse

                when (result) {
                    is List<Kviz> -> onSuccess?.invoke(result)
                    else -> onError?.invoke()
                }
            }
        }
    }

    fun getKvizById(kvizId:Int,onSuccess: (predan: String) -> Unit,
                    onError: () -> Unit) {
        scope.launch {
            val result = KvizRepository.getKvizById(kvizId)
            when (result) {
                is Kviz -> onSuccess?.invoke(result.predan)
                else -> onError?.invoke()
            }
        }
    }

    fun searchDB(flag: Int,onSuccess: (kvizovi: List<Kviz>) -> Unit,
                 onError: () -> Unit) {
        if(flag == 5) {
            scope.launch {
                val result = KvizRepository.getUpisaniDB()
                when (result) {
                    is List<Kviz> -> onSuccess?.invoke(result)
                    else -> onError?.invoke()
                }
            }
        }
        else if(flag==3) {
            scope.launch {
                val result = KvizRepository.getUpisaniDB()
                when (result) {
                    is List<Kviz> -> onSuccess?.invoke(result.filter { it-> SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(it.datumPocetka).after(Calendar.getInstance().time) })
                    else -> onError?.invoke()
                }
            }
        }
        else {
            scope.launch {
                val result = KvizRepository.getUpisaniDB()
                when (result) {
                    is List<Kviz> -> onSuccess?.invoke(result.filter { it-> it.predan == "true" })
                    else -> onError?.invoke()
                }
            }
        }
    }
}