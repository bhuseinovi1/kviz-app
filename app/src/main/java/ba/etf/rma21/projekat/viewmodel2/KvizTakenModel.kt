package ba.etf.rma21.projekat.viewmodel2

import ba.etf.rma21.projekat.data.models.KvizTaken
import ba.etf.rma21.projekat.data.models.Odgovor
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.repositories.OdgovorRepository
import ba.etf.rma21.projekat.data.repositories.TakeKvizRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class KvizTakenModel {
    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun getPokusajiZaKviz(idKviz: Int,onSuccess: (pokusaji: List<KvizTaken>) -> Unit,
                onError: () -> Unit) {
        scope.launch {
            val result = OdgovorRepository.getPokusajiZaKviz(idKviz) // ovo je tipa GetPredmetResponse

            when(result) {
                is List<KvizTaken> -> onSuccess?.invoke(result)
                else-> onError?.invoke()
            }
        }
    }

    fun zapocni(pitanja: List<Pitanje>,kvizId: Int, onSuccess: (pitanja: List<Pitanje>,kvizTakenId: Int,kvizId: Int) -> Unit,
               onError: () -> Unit) {
        scope.launch {
            val result = TakeKvizRepository.zapocniKviz(kvizId) // ovo je tipa GetPredmetResponse
            when(result) {
                is KvizTaken -> onSuccess?.invoke(pitanja,result.id,kvizId)
                else-> onError?.invoke()
            }
        }
    }

    fun provjeriJeLiZapocet(pitanja: List<Pitanje>, kvizId: Int, onSuccess: (pitanja: List<Pitanje>,kvizId:Int,idPokusaja: Int) -> Unit,
                            onError: (pitanja: List<Pitanje>,kvizId:Int) -> Unit) {
        scope.launch {
            val result = TakeKvizRepository.getPocetiKvizovi()// ovo je tipa GetPredmetResponse
            var tacno = false
            print(result)





            when(result) {
                is List<KvizTaken> -> {
                    for(kt in result) {

                        if(kt.KvizId == kvizId) {
                            tacno = true
                            /*
                            val toast = Toast.makeText(contekst,result.toString()+"\n"+kvizId.toString()+"\n"+"SUCCESS", Toast.LENGTH_LONG)
                            toast.show();

                             */
                            onSuccess?.invoke(pitanja,kvizId,kt.id)
                        }
                    }
                    if(tacno == false) {
                        /*
                        val toast = Toast.makeText(contekst,result.toString()+"\n"+kvizId.toString()+"\n"+"ERROR", Toast.LENGTH_LONG)
                        toast.show();

                         */
                        onError?.invoke(pitanja,kvizId)
                    }
                }
                else-> onError?.invoke(pitanja,kvizId)
            }
        }
    }

    fun odgovori(kvizId: Int,onSuccess: () -> Unit,
                onError: () -> Unit) {
        scope.launch {
            val result = OdgovorRepository.predajOdgovore(kvizId)
            //val result = OdgovorRepository.postaviOdgovorKviz(kvizTakenId,idPitanje,odgovor) // ovo je tipa GetPredmetResponse

            when(result) {
                is Any -> onSuccess?.invoke()
                else-> onError?.invoke()
            }
        }
    }

    fun dajOdgovore(kvizTakenId: Int ,onSuccess: (result: List<Odgovor>) -> Unit,
                 onError: () -> Unit) {

        scope.launch {
            val result = OdgovorRepository.getOdgovoriKvizByKvizTaken(kvizTakenId)

            if(result!!.size == 0) {
                /*val toast = Toast.makeText(conteks,result.toString() + kvizTakenId.toString(),Toast.LENGTH_LONG)
                toast.show();

                 */
                onError?.invoke()
            }
            else {
                onSuccess?.invoke(result)
            }
            /*
            when(result) {
                is -> onSuccess?.invoke()
                else-> onError?.invoke()
            }

             */
        }
    }

}