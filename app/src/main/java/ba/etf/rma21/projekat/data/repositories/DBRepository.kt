package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.widget.Toast
import ba.etf.rma21.projekat.data.AppDatabase
import ba.etf.rma21.projekat.data.api.ApiAdapter
import ba.etf.rma21.projekat.data.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class DBRepository {
    companion object {
        private lateinit var context: Context
        fun setContext(_context: Context) {
            context=_context
        }
        suspend fun updateNow(): Boolean? {

            var changed = withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                var response = ApiAdapter.retrofit.biloPromjena(AccountRepository.getHash(),db.accountDao().getLastUpdate())
                val responseBody = response.body()
                return@withContext responseBody
            }

            if (changed != null) {
                if(changed.changed == true) {
                    //Toast.makeText(context,"UPDATE!!!",Toast.LENGTH_LONG).show()

                    // sve grupe sa APIja

                    var grupe = PredmetIGrupaRepository.getUpisaneGrupe()
                    try {
                        if (grupe != null) {
                            for (grupa in grupe.distinct()) {
                                PredmetIGrupaRepository.addGrupa(grupa)
                            }
                        }
                    }
                    catch (e: SQLiteConstraintException) {

                    }


                    var kvizovi = emptyList<Kviz>()
                    // svi kvizovi sa APIja
                    try {
                        kvizovi = KvizRepository.getUpisani()!!
                        for (kviz in kvizovi!!.distinct()) {
                            if (kviz.datumKraj == null || kviz.datumKraj=="") kviz.datumKraj = ""
                            else {
                                var dateKraj = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(kviz.datumKraj)
                                kviz.datumKraj = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(dateKraj)
                            }
                            var datePocetak = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(kviz.datumPocetka)
                            kviz.datumPocetka = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(datePocetak)
                            //KvizRepository.addKviz(kviz)

                            // ako ga ima u pokusajima onda true, ako nema, false
                            var predan = "false"
                            var tks = TakeKvizRepository.getPocetiKvizovi()
                            if (tks != null) {
                                for(tk in tks) {
                                    if(tk.KvizId == kviz.id) predan = "true";
                                }
                                if(predan=="false") {
                                    KvizRepository.addKviz(Kviz(kviz.id, kviz.naziv, kviz.datumPocetka, kviz.datumKraj, kviz.trajanje, "false"))
                                }
                                else {
                                    KvizRepository.addKviz(Kviz(kviz.id, kviz.naziv, kviz.datumPocetka, kviz.datumKraj, kviz.trajanje, "true"))
                                }
                            }
                            else {
                                KvizRepository.addKviz(Kviz(kviz.id, kviz.naziv, kviz.datumPocetka, kviz.datumKraj, kviz.trajanje, "false"))
                            }
                        }
                    }
                    catch (e: SQLiteConstraintException) {

                    }

                    // svi predmeti sa APIja
                    var predmeti: MutableList<Predmet> = arrayListOf()
                    var pitanja: MutableList<Pitanje> = arrayListOf()
                    try {
                        for (kviz in kvizovi) {
                            PredmetIGrupaRepository.getPredmetZaKviz(kviz.id)?.let { predmeti.addAll(it) }
                            PitanjeKvizRepository.getPitanja(kviz.id)?.let { pitanja.addAll(it) }
                        }
                    }
                    catch (e: SQLiteConstraintException) {

                    }

                    try {
                        for (pitanje in pitanja.distinct()) PitanjeKvizRepository.addPitanja(pitanje)
                    }
                    catch (e: SQLiteConstraintException) {}

                    try {
                        for (predmet in predmeti.distinct()) PredmetIGrupaRepository.addPredmet(predmet)
                    }
                    catch (e: SQLiteConstraintException) {

                    }

                    withContext(Dispatchers.IO) {
                        var db = AppDatabase.getInstance(context)
                        db.accountDao().updatujLastUpdate(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(Calendar.getInstance().time))
                    }
                }
                else {
                    //Toast.makeText(context,"NIJE UPDATE!!!",Toast.LENGTH_LONG).show()
                }
            }
            return changed!!.changed
        }

        suspend fun dajOdgovoreDB(): List<Odgovor> {
            return withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                return@withContext db.odgovorDao().getAll()
            }
        }

        // samo iz DBModela koji je prethodno pozvan iz PitanjeFragment
        suspend fun dajOdgovoreZaKvizTakenAndPitanjeDB(kvizTakenId: Int,pitanjeId: Int): List<Odgovor> {
            return withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                return@withContext db.odgovorDao().getOdgovorByPitanjeAndKvizTaken(pitanjeId,kvizTakenId)
            }
        }

        suspend fun updateOdgovor(kvizId: Int,idPokusaja: Int, pitanjeId: Int) {
            withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                db.odgovorDao().updateujOdgovor(kvizId,idPokusaja,pitanjeId)
            }
        }

        suspend fun dajOdgovoreZaKvizDB(kvizId: Int): List<Odgovor> {
            return withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                return@withContext db.odgovorDao().getForKviz(kvizId)
            }
        }
    }
}