package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.modelsOLD.Grupa
import ba.etf.rma21.projekat.data.staticData.grupe

class `Z-GrupaRepository` {
    companion object {
        init {
            // TODO: Implementirati
        }

        fun getGroupsByPredmet(nazivPredmeta: String): List<Grupa> {
            return grupe().filter { it.nazivPredmeta == nazivPredmeta }
        }
    }
}