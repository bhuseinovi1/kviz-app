package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet

@Dao
interface PredmetDao {
    @Query("SELECT * FROM Predmet")
    suspend fun getAll(): List<Predmet>

    @Query("SELECT * FROM Predmet WHERE id=:id")
    suspend fun findById(id: Int): Predmet

    @Query("DELETE FROM Predmet")
    suspend fun deleteAllPredmeti()

    @Insert
    suspend fun insertAll(vararg odgovors: Predmet)

/*


    @Query("SELECT id,naziv,godina FROM Predmet, PredmetKviz WHERE Predmet.id=PredmetKviz.idPredmeta AND PredmetKviz.idKviza=:kvizId")
    suspend fun getGrupeZaKviz(kvizId: Int): List<Grupa>

     */
}