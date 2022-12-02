package ba.etf.rma21.projekat.viewmodel2

import ba.etf.rma21.projekat.data.models.Odgovor
import ba.etf.rma21.projekat.data.repositories.DBRepository
import ba.etf.rma21.projekat.data.repositories.OdgovorRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DBModel {
    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun updateuj() {
        scope.launch {
            DBRepository.updateNow()
        }
    }

    fun dodajOdgovorDB(idKvizTaken: Int, PitanjeId: Int, odgovoreno: Int,onSuccess: ()-> Unit,
                       onError: () -> Unit) {
        scope.launch {
            //DBRepository.dodajOdgovorDB(idKvizTaken, PitanjeId, odgovoreno)
            OdgovorRepository.postaviOdgovorKviz(idKvizTaken,PitanjeId,odgovoreno)
            onSuccess?.invoke()
        }
    }

    fun dajOdgovoreDB(onSuccess: (result: List<Odgovor>) -> Unit,
                      onError: () -> Unit) {
        scope.launch {
            var odgovori = DBRepository.dajOdgovoreDB()
        }
    }

    // ovo koristimo samo u PitanjeFragment
    fun dajOdgovoreZaKvizTakenAndPitanjeDB(kvizTakenId: Int,pitanjeId: Int,onSuccess: (result: List<Odgovor>) -> Unit,
                                 onError: () -> Unit)  {
        scope.launch {
            var odgovor = DBRepository.dajOdgovoreZaKvizTakenAndPitanjeDB(kvizTakenId,pitanjeId)
            when (odgovor) {
                is List<Odgovor> -> onSuccess?.invoke(odgovor)
                else -> onError?.invoke()
            }
        }
    }

    fun updateujOdgovor(idPokusaja: Int, pitanjeId: Int, kvizId: Int) {
        scope.launch {
            DBRepository.updateOdgovor(kvizId,idPokusaja,pitanjeId)
        }
    }

    fun dajOdgovoreZaKvizDB(kvizId: Int, onSuccess: (result: List<Odgovor>,kvizId: Int) -> Unit, onError: () -> Unit){

        scope.launch {
            var odgovori = DBRepository.dajOdgovoreZaKvizDB(kvizId)
            when (odgovori) {
                is List<Odgovor> -> onSuccess?.invoke(odgovori,kvizId)
                else -> onError?.invoke()
            }
        }
    }
}