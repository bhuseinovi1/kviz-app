package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import android.widget.Toast
import ba.etf.rma21.projekat.BuildConfig
import ba.etf.rma21.projekat.data.AppDatabase
import ba.etf.rma21.projekat.data.api.ApiAdapter
import ba.etf.rma21.projekat.data.models.Account
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.viewmodel2.AccountModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class AccountRepository {
    companion object {
        var acHash: String = BuildConfig.API_KEY

        private lateinit var context:Context

        fun setContext(_context: Context) {
            context = _context
        }

        suspend fun deleteTableData(acHash: String) : Boolean {
            if(getHash() != acHash) {
                withContext(Dispatchers.IO) {
                    PredmetIGrupaRepository.deleteAllGrupe()
                    PredmetIGrupaRepository.deleteAllPredmeti()
                    PitanjeKvizRepository.deleteAllKvizovi()
                    PitanjeKvizRepository.deleteAllPitanja()
                    OdgovorRepository.deleteAllOdgovori()
                }
                return false
            }
            return true
        }

        suspend fun postaviHash(acHash:String) : Boolean {
            var isti = deleteTableData(acHash)

            /*
            var student = withContext(Dispatchers.IO) {
                return@withContext ApiAdapter.retrofit.getStudentById(getHash()).body()
            } // ovo moze vratit i dummy studenta

             */

            this.acHash = acHash // ovo je sad "novi" hash

            withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                val studenti = db.accountDao().getAll() // provjerava da li je tabela Account prazna
                if(studenti.isEmpty()) {
                    withContext(Dispatchers.IO) {
                        var calendar = Calendar.getInstance()
                        calendar.add(Calendar.YEAR,-1)
                        db!!.accountDao().insertAll(Account(1, "", acHash, SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(calendar.time)))
                    }
                }
                else {
                    withContext(Dispatchers.IO) {
                        db!!.accountDao().updatujAcc(acHash)
                        var calender = Calendar.getInstance()
                        calender.add(Calendar.YEAR,-1)
                        if(!isti)
                        db!!.accountDao().updatujLastUpdate(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(calender.time))
                    }
                }
            }
            return isti
        }
        fun getHash(): String {
            // i ovo treba dobavljati iz baze(ako postoji tabela account tu)
            return this.acHash
        }

        suspend fun upisiAccUBazu(context: Context,account: Account) : String ?{
            return withContext(Dispatchers.IO) {
                try {
                    var db = AppDatabase.getInstance(context)
                    db!!.accountDao().insertAll(account)
                    return@withContext "success"
                }
                catch (error: Exception) {
                    return@withContext null
                }
            }
        }

        suspend fun obrisiAccPodatke() {
            withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.obrisiPodatkeZaStudenta(AccountRepository.getHash())
                val responseBody = response.body()
                return@withContext responseBody
            }

        }

        suspend fun getStudentaZaId(hash: String) : Account? {
            return withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.getStudentById(hash)
                val responseBody = response.body()
                return@withContext responseBody
            }

        }
    }
}