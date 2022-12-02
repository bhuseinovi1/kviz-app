package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import ba.etf.rma21.projekat.data.AppDatabase
import ba.etf.rma21.projekat.data.api.ApiAdapter
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class PredmetIGrupaRepository {
    companion object {
        // TODO: Implementirati
        init {
            // TODO: Implementirati
        }

        private lateinit var context:Context
        fun setContext(_context: Context){
            context=_context
        }

        suspend fun getPredmeti(): List<Predmet>? {
            return withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.getPredmeti()
                val responseBody = response.body()
                return@withContext responseBody
            }

        }

        suspend fun getPredmetById(idPredmet: Int): Predmet? {
            return withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.getPredmetById(idPredmet)
                val responseBody = response.body()
                return@withContext responseBody
            }
        }

        suspend fun getGrupe(): List<Grupa>? {
            return withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.getGrupe()
                val responseBody = response.body()
                return@withContext responseBody
            }

        }

        suspend fun getGrupaById(idGrupa: Int): Grupa? {
            return withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.getGrupaById(idGrupa)
                val responseBody = response.body()
                return@withContext responseBody
            }
        }

        suspend fun getGrupeZaPredmet(idPredmeta:Int): List<Grupa>? {
            return withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.getGrupeZaPredmet(idPredmeta)
                val responseBody = response.body()
                if (responseBody != null) {
                    if(responseBody.size == 0) return@withContext emptyList<Grupa>()
                }
                return@withContext responseBody
            }
        }

        suspend fun getGrupeZaKviz(idKviza:Int): List<Grupa>? {
            return withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.getGrupeZaKviz(idKviza)
                val responseBody = response.body()
                if (responseBody != null) {
                    if(responseBody.size == 0) return@withContext emptyList<Grupa>()
                }
                return@withContext responseBody
            }
        }

        // my method
        suspend fun getPredmetZaKviz(idKviza:Int): List<Predmet>? {

            var ggzk = withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.getGrupeZaKviz(idKviza)
                val responseBody = response.body()
                if (responseBody != null) {
                    if(responseBody.size == 0) return@withContext emptyList<Grupa>()
                }
                return@withContext responseBody
            }

            var predmeti: MutableList<Predmet> = arrayListOf()

            if (ggzk != null) {
                for(g in ggzk) {
                    predmeti.add(withContext(Dispatchers.IO) {
                        var response = ApiAdapter.retrofit.getPredmetById(g.PredmetId)
                        val responseBody = response.body()
                        return@withContext responseBody
                    }!!)
                }
            }
            return predmeti.toSet().toList()
        }

        // pogresna povratna
        suspend fun upisiUGrupu(idGrupa:Int): Boolean {
            // ovdje zapisi modifikaciju - updatovanje lastUpdated
            //var db = AppDatabase.getInstance(context)
            //db.accountDao().updatujLastUpdate("2010-05-12T11:33:33")
            //db.accountDao().updatujLastUpdate( SimpleDateFormat("yyyy-mm-ddThh:mm:ss").format(Calendar.getInstance().time))


            var rez = withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.dodajUGrupuSaId(idGrupa,AccountRepository.getHash())
                val responseBody = response.body()
                return@withContext responseBody
            }
            if (rez != null) {
                if(rez.message.contains("Ne postoji") || rez.message.contains("found"))
                return false
            }
            return true
        }

        suspend fun getUpisaneGrupe(): List<Grupa>? {
            try {
                return withContext(Dispatchers.IO) {
                    var response =
                        ApiAdapter.retrofit.getGrupeZaStudenta(AccountRepository.getHash())
                    val responseBody = response.body()
                    if (responseBody==null) return@withContext emptyList<Grupa>()
                    if (responseBody.size == 0) return@withContext emptyList<Grupa>()
                    return@withContext responseBody
                }
            }
            catch (e:Exception) {
                return emptyList<Grupa>()
            }
        }

        suspend fun deleteAllGrupe() {
            return withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                db!!.grupaDao().deleteAllGrupe()
            }
        }

        suspend fun deleteAllPredmeti() {
            return withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                db!!.predmetDao().deleteAllPredmeti()
            }
        }

        suspend fun addGrupa(grupa: Grupa) {
            return withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                db!!.grupaDao().insertAll(grupa)
            }
        }

        suspend fun addPredmet(predmet: Predmet) {
            return withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                db!!.predmetDao().insertAll(predmet)
            }
        }


    }
}