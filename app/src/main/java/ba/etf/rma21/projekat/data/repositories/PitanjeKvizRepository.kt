package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import ba.etf.rma21.projekat.data.AppDatabase
import ba.etf.rma21.projekat.data.api.ApiAdapter
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.Pitanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PitanjeKvizRepository {
    companion object{
        /*
        fun getPitanja(nazivKviza:String,nazivPredmeta:String):List<ba.etf.rma21.projekat.data.responses.Pitanje>{ // ? -> naziv kviza i naziv predmeta nisu dovoljan identifikator za generisanje pitanja
            //todo Implementirati metodu da ispravno vraća rezultat
            var predmeti = predmeti().filter { it.naziv == nazivPredmeta }
            var kvizovi = kvizovi().filter { it.nazivPredmeta == predmeti[0].naziv && it.naziv == nazivKviza }
            var pitanjaKviz = pitanjaKviz().filter { it.nazivKviza == kvizovi[0].naziv } // ovo ce vratit sve potrebne kombinacije pitanje - nazivKviza


            // kolektovanje svih pitanja
            var pitanja: MutableList<ba.etf.rma21.projekat.data.responses.Pitanje> = arrayListOf()

            for(i in 0..pitanjaKviz.size-1) {
                pitanja.add(pitanjaKviz[i].pitanje)
            }

            return pitanja;

            //return pitanja().filter { it.}
            //return listOf(Pitanje("p1","Tačan odgovor je b", listOf("a","b","c"),1), Pitanje("p2","Tačan odgovor je c", listOf("a","b","c"),2))
        }

         */

        private lateinit var context:Context
        fun setContext(_context: Context){
            context=_context
        }

        suspend fun deleteAllPitanja() {
            return withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                db!!.pitanjeDao().deleteAllPitanja()
            }
        }

        suspend fun deleteAllKvizovi() {
            return withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                db!!.kvizDao().deleteAllKvizovi()
            }
        }

        suspend fun addPitanja(pitanja: Pitanje) {
            return withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                db!!.pitanjeDao().insertAll(pitanja)
            }
        }




        suspend fun getPitanja(idKviza:Int): List<Pitanje>? {
            return  withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.getPitanjaZaKviz(idKviza)
                val responseBody = response.body()
                if(responseBody == null) return@withContext emptyList<Pitanje>()
                if(responseBody.size == 0) return@withContext emptyList<Pitanje>()
                return@withContext responseBody
            }
        }
    }
}