package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import ba.etf.rma21.projekat.data.AppDatabase
import ba.etf.rma21.projekat.data.api.ApiAdapter
import ba.etf.rma21.projekat.data.models.KvizTaken
import ba.etf.rma21.projekat.data.models.Odgovor
import ba.etf.rma21.projekat.data.models.OdgovorParsed
import ba.etf.rma21.projekat.data.models.Pitanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody
import org.json.JSONObject
import java.util.*

class OdgovorRepository {
    companion object {
        private lateinit var context: Context
        fun setContext(_context: Context){
            context=_context
        }

        suspend fun getPokusajiZaKviz(idKviz: Int): List<KvizTaken>? {
            var rez : List<KvizTaken>? = withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.getPokusajiZaStudenta(AccountRepository.getHash())
                val responseBody = response.body()
                return@withContext responseBody
            }
            var pokusaji: MutableList<KvizTaken> = arrayListOf<KvizTaken>()

            if (rez != null) {
                if(rez.size == 0) return null // kviz postoji ali nije bilo pokusaja
                for(k in rez) {
                    if(k.KvizId == idKviz) pokusaji.add(k)
                }
            }
            else return null
            return pokusaji
        }

        suspend fun getOdgovoriKviz(idKviz: Int): List<Odgovor>? {
            // prvo treba iskoristiti rutu /student/{id}/kviztaken
            var rez = getPokusajiZaKviz(idKviz)

            if (rez != null) {
                    if(rez.size != 0) {
                        return withContext(Dispatchers.IO) {
                            var response = ApiAdapter.retrofit.getOdgovoriZaKvizTakeniStudenta(AccountRepository.getHash(),rez[0].id)
                            val responseBody = response.body()
                            return@withContext responseBody
                        }
                    }
                    return emptyList<Odgovor>()
            }
            // debatable da li null ili prazna lista
            return emptyList<Odgovor>()
            //return null
        }

        suspend fun getOdgovoriKvizByKvizTaken(idKvizTaken: Int): List<Odgovor>? {
            // prvo treba iskoristiti rutu /student/{id}/kviztaken

                    return withContext(Dispatchers.IO) {
                        var response = ApiAdapter.retrofit.getOdgovoriZaKvizTakeniStudenta(AccountRepository.getHash(),idKvizTaken)
                        val responseBody = response.body()
                        if (responseBody != null) {
                            if(responseBody.size == 0) return@withContext emptyList<Odgovor>()
                        }
                        return@withContext responseBody
                    }
            // debatable da li null ili prazna lista
            return emptyList<Odgovor>()
            //return null
        }




        suspend fun deleteAllOdgovori() {
            return withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                db!!.odgovorDao().deleteAllOdgovori()
            }
        }
        // greska u povratnom tipu - treba int
        /**
         * Funkcija postavlja odgovor sa indeksom odgovor na pitanje sa id-em idPitanje u okviru pokušaja sa id-em idKvizTaken. Funkcija vraća ukupne
         * bodove na kvizu nakon odgovora ili -1 ukoliko ima neka greška u zahtjevu.
         */

        suspend fun postaviOdgovorKviz(idKvizTaken: Int, idPitanje: Int, odgovor: Int) : Int {
            // povuci sa API-ja sve kvizTakene ( SIGURNO IH IMA )
            var brojBodova = 0
            var idKviz = 0

            var rez : List<KvizTaken>? = withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.getPokusajiZaStudenta(AccountRepository.getHash())
                val responseBody = response.body()
                return@withContext responseBody
            }

            if (rez != null) {
                if(rez.isEmpty()) return 0
                for(kt in rez) {
                    if(kt.id == idKvizTaken) {
                        idKviz = kt.KvizId
                    }
                }
            }
            else return 0
            // ovdje cu dobiti kvizID

            // ako postoji vec u bazi odgovor ciji je isti kviztaken i pitanje, ne radi nista
            var odgovori = withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                return@withContext db.odgovorDao().getOdgovorByPitanjeAndKvizTaken(idPitanje,idKvizTaken)
            }

            if(odgovori.isEmpty()) { // ako nema tog odgovora
                withContext(Dispatchers.IO) {
                    var db = AppDatabase.getInstance(context)
                    //db.odgovorDao().insertAll(Odgovor((1..1000).random(),odgovor, idPitanje, idKvizTaken,0))
                    db.odgovorDao().insertAll(Odgovor((1..1000).random(),odgovor, idPitanje, idKvizTaken,idKviz))
                }
            }

            var odgovoriSviZaPokusaj = withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                return@withContext db.odgovorDao().getForKvizTaken(idKvizTaken)
            }

            var pitanjaZaKviz : List<Pitanje>? = withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.getPitanjaZaKviz(idKviz)
                val responseBody = response.body()
                return@withContext responseBody
            }

            //var pocetniBrojBodova =
            var brojPitanja = pitanjaZaKviz!!.size
            for(pitanje in pitanjaZaKviz) {
                for(odgovor in odgovoriSviZaPokusaj) {
                    if(odgovor.PitanjeId == pitanje.id) {
                        if(odgovor.odgovoreno == pitanje.tacan) {
                            brojBodova += 100/brojPitanja
                        }
                    }
                }
            }

            return brojBodova
        // sracunaj bodove

        }

        suspend fun predajOdgovore(idKviz: Int) {
            var odgovori = withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                return@withContext db.odgovorDao().getOdgovoreZaKviz(idKviz)
            }
            //Toast.makeText(context,odgovori.toString(),Toast.LENGTH_LONG).show()
            for (odgovor in odgovori) {
                postaviOdgovorKvizAPI(odgovor.KvizTakenId, odgovor.PitanjeId,odgovor.odgovoreno)
            }

            // postavi kvizId tu
            withContext(Dispatchers.IO) {
                var db = AppDatabase.getInstance(context)
                db.kvizDao().postaviPredan(idKviz)
            }

            // prvo treba naci sve odgovore za ovaj kviz, onda za svaki od tih odgovora uputiti zahtjev prema servisu
            // te upisati na mjestu u tabeli Kviz sa id-om kvizId vrijednost True u kolonu predan
        }


        suspend fun postaviOdgovorKvizAPI(idKvizTaken: Int, idPitanje: Int, odgovor: Int) : Int {
            //postaviOdgovor(idKvizTaken,idPitanje,odgovor)


            // dodati odgovor u bazu
            //var db = AppDatabase.getInstance(context)
            //db.odgovorDao().insertAll(Odgovor(0,odgovor,idPitanje,idKvizTaken))
            //db.accountDao().updatujLastUpdate( SimpleDateFormat("yyyy-mm-ddThh:mm:ss").format(Calendar.getInstance().time))


            // zbog trećeg zadatka ovo vise nema smisla


            // ovdje ima još mnogo posla
            // prvo je potrebno provjeriti da li je odgovor:Int pravi odgovor na pitanje sa id-om idPitanje:Int
            var rez = withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.getPokusajiZaStudenta(AccountRepository.getHash())
                val responseBody = response.body()
                return@withContext responseBody
            }
            var KVIZID = 0
            var brojBodova : Int = 0
            // sada treba naci zeljeni pokusaj
            if (rez != null) {
                for(p in rez) {
                    if (p.id == idKvizTaken) {
                        KVIZID = p.KvizId
                        brojBodova += p.osvojeniBodovi
                    }
                }
            }

            //println(KVIZID) // uspješno pronađen kvizID = 1
            //println(brojBodova) // brojBodova= 0

            if(KVIZID==0) return -1 // ovdje je sigurno greška u zahtjevu jer nismo pronasli pokusaj koji je proslijeđen

            // sada treba ispitati da li je tacan odgovor, ako jeste, azuriraj bodove
            var rez2 = withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.getPitanjaZaKviz(KVIZID)
                val responseBody = response.body()
                return@withContext responseBody
            }

            //println(rez2) // uspješno nalazi potrebna pitanja za kviz

            if(rez2 == null) return -1
            if(rez2.size == 0) return -1

            // idemo kroz pitanja
            for(pitanje in rez2) {
                if(pitanje.id == idPitanje) {
                    // ako je odgovor tacan
                    if(pitanje.tacan == odgovor) {
                        // ukupno kviz nosi 100 bodova, tako da bi broj bodova na ovo pitanje nosio
                        brojBodova += (100 / rez2.size) // ovo ovdje smijemo uraditi jedino ako je donji upit ka web servisu bio uspješan
                    }
                }
            }

            //println(brojBodova) // uspješno našao 50 bodova

            println(AccountRepository.acHash + " "+ idKvizTaken + " " + odgovor + " " + idPitanje + " " + brojBodova)
            try {
                withContext(Dispatchers.IO) {
                    //println("POCETAK")
                    /*
                    var paramObject: JSONObject = JSONObject();
                    paramObject.put("odgovor",odgovor)
                    paramObject.put("pitanje",idPitanje)
                    paramObject.put("bodovi",brojBodova)


                     */

                    //parser(idKvizTaken,odgovor,idPitanje, brojBodova)

                    ApiAdapter.retrofit.dodajOdgovorZaKvizTakeniStudenta(
                        AccountRepository.getHash(),
                        idKvizTaken,
                        OdgovorParsed(odgovor,idPitanje,brojBodova)
                    )

                    /*ApiAdapter.retrofit.dodajOdgovorZaKvizTakeniStudenta(
                        AccountRepository.getHash(),
                        idKvizTaken,
                        odgovor,
                        idPitanje,
                        brojBodova
                    )

                     */

                    //print(response)
                    //val responseBody = response.body()
                    //return@withContext responseBody
                }
                return brojBodova
            }
            catch (e: Exception) {
                //print(e)
                //println("EXCEPTION") // otislo je u exception
                return brojBodova - (100 / rez2.size)
            }
        }

        private fun createJsonRequestBody(vararg params: Pair<String,Int>) =
            RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),JSONObject(
                mapOf(*params)).toString())
    }
}