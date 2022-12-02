package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import ba.etf.rma21.projekat.data.AppDatabase
import ba.etf.rma21.projekat.data.api.ApiAdapter
import ba.etf.rma21.projekat.data.models.KvizTaken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class TakeKvizRepository {
    companion object {

        private lateinit var context:Context
        fun setContext(_context: Context){
            context=_context
        }

        /**
         * Kreira pokušaj za kviz, vraća kreirani pokušaj ili null u slučaju greške.
         */
        suspend fun zapocniKviz(idKviza: Int): KvizTaken? {

            // update-uj last-modified
            //var db = AppDatabase.getInstance(context)
            //db.accountDao().updatujLastUpdate( SimpleDateFormat("yyyy-mm-ddThh:mm:ss").format(Calendar.getInstance().time))


            try {
                return withContext(Dispatchers.IO) {
                    var response = ApiAdapter.retrofit.zapocniKvizZaStudenta(
                        AccountRepository.getHash(),
                        idKviza
                    )
                    val responseBody = response.body()
                    if (responseBody != null) { // ako nema kviz taken, ono u biti napravi kviztaken sa id-om 0, studntom null i sl.
                        if(responseBody.id==0) return@withContext null
                    }
                    return@withContext responseBody
                }
            }
            catch (e: Exception) {
                return null
            }
        }

        /**
         * Vraća listu pokušaja ili null ukoliko student nema niti jedan započeti kviz.
         */
        suspend fun getPocetiKvizovi(): List<KvizTaken>? {
            try {
                return withContext(Dispatchers.IO) {
                    var response =
                        ApiAdapter.retrofit.getPokusajiZaStudenta(AccountRepository.getHash())
                    val responseBody = response.body()
                    if(responseBody==null) return@withContext null
                    if(responseBody.size == 0) return@withContext null
                    return@withContext responseBody
                }
            }
            catch (e:Exception) {
                return null
            }
        }
    }
}