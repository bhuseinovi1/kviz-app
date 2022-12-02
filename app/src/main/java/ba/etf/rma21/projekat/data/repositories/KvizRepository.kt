package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import ba.etf.rma21.projekat.data.AppDatabase
import ba.etf.rma21.projekat.data.api.ApiAdapter
import ba.etf.rma21.projekat.data.modelsOLD.Korisnik
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.Grupa
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class KvizRepository {

    companion object {
        // TODO: Implementirati
        init {
            // TODO: Implementirati
        }

        private lateinit var context: Context
        fun setContext(_context: Context){
            context=_context
        }

        fun getKvizoviByPredmetAndGrupa(nazivPredmeta: String, nazivGrupe: String): List<Kviz>? {
            return null;
            //return getAll().filter { it.nazivPredmeta == nazivPredmeta && it.nazivGrupe == nazivGrupe}
        }


        fun getMyKvizes(): List<ba.etf.rma21.projekat.data.modelsOLD.Kviz> {
            //return mojiKvizovi();
            return Korisnik.dajKvizove()
        }



        suspend fun getAll(): List<Kviz>? {
            return withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.getKvizovi()
                val responseBody = response.body()
                return@withContext responseBody
            }
            // ispraviti
            //return kvizovi()
        }

        suspend fun getById(id:Int): Kviz? {
            try {
                return withContext(Dispatchers.IO) {
                    var response = ApiAdapter.retrofit.getKvizById(id)
                    val responseBody = response.body()
                    if (responseBody != null) {
                        if(responseBody.id == 0) return@withContext null
                    }
                    return@withContext responseBody
                }
            }
            catch (e: Exception) {
                return null
            }

        }

        suspend fun getUpisani(): List<Kviz>? {
            // prvo se trebaju dofatit sve grupe na kojima je upisan student
            var grupe : List<Grupa>? = withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.getGrupeZaStudenta(AccountRepository.getHash())
                val responseBody = response.body()
                return@withContext responseBody
            }
            var kvizovi: MutableList<Kviz> = mutableListOf()
            if (grupe != null) {
                if(grupe.size == 0) return emptyList<Kviz>()
                /* moram ispravit zbog duplih kvizova
                for (grupa in grupe) {
                    kvizovi.addAll(withContext(Dispatchers.IO) {
                        var response = ApiAdapter.retrofit.getKvizoviZaGrupu(grupa.id)
                        val responseBody = response.body()
                        return@withContext responseBody
                    }!!)
                }

                 */
                for (grupa in grupe) {
                    var kvizovi1 = withContext(Dispatchers.IO) {
                        var response = ApiAdapter.retrofit.getKvizoviZaGrupu(grupa.id)
                        val responseBody = response.body()
                        return@withContext responseBody
                    }!!
                    for (k in kvizovi1) {
                        if(!kvizovi.contains(k)) kvizovi.add(k)
                    }
                }
            }
            return kvizovi
            // sada treba dohvatiti sve kvizove za grupe
        }

        suspend fun addKviz(kviz: Kviz) {
            return withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                db!!.kvizDao().insertAll(kviz)
            }
        }

        suspend fun getKvizById(kvizId: Int) : Kviz{
            return withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                return@withContext db!!.kvizDao().getKvizById(kvizId)
            }
        }

        suspend fun getUpisaniDB(): List<Kviz> {
            return withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                return@withContext db!!.kvizDao().getAll()
            }
        }

        /*
        fun getDone(): List<Kviz> {
            return getMyKvizes().filter { it.osvojeniBodovi != null }
        }

        fun getFuture(): List<Kviz> {
            return getMyKvizes().filter { it.datumPocetka.after(Calendar.getInstance().time) }
        }

        fun getNotTaken(): List<Kviz> {
            return getMyKvizes().filter { /*it.datumPocetka.before(Calendar.getInstance().time) && it.datumKraj.after(Calendar.getInstance().time) && it.osvojeniBodovi == null || */ it.datumKraj.before(Calendar.getInstance().time) && it.osvojeniBodovi == null}
        }

         */
        // TODO: Implementirati i ostale potrebne metode
    }
}