package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.api.ApiAdapter
import ba.etf.rma21.projekat.data.models.Predmet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class `Z-PredmetRepository` {
    companion object {
        /*
        fun getUpisani(): List<Predmet> {
            // TODO: Implementirati
            return emptyList()
        }

         */

        /*
        fun getAllOld(): List<Predmet> {
            return predmeti();
        }

         */

        suspend fun getAll(): List<Predmet>? {
            return withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.getPredmeti()
                val responseBody = response.body()
                return@withContext responseBody
            }
        }

        /*
        fun getPredmetsByGodina(godina: Int): List<Predmet> {
            return predmeti().filter { it.godina == godina }
        }

         */

        // TODO: Implementirati i ostale potrebne metode
    }

}