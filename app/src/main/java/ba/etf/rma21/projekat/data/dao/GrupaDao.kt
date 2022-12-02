package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Grupa

@Dao
interface GrupaDao {
    @Query("SELECT * FROM Grupa")
    suspend fun getAll(): List<Grupa>

    @Query("SELECT * FROM Grupa WHERE id=:id")
    suspend fun getGrupeById(id: Int): Grupa

    @Query("DELETE FROM Grupa")
    suspend fun deleteAllGrupe()

    @Query("SELECT * FROM Grupa WHERE PredmetId=:predmetId")
    suspend fun getGrupeZaPredmet(predmetId: Int): List<Grupa>

    @Insert
    suspend fun insertAll(vararg grupe: Grupa)
    /*
    @Query("SELECT id,naziv,PredmetId FROM Grupa, GrupaKviz WHERE Grupa.id=GrupaKviz.idGrupe AND GrupaKviz.idKviza=:kvizId")
    suspend fun getGrupeZaKviz(kvizId: Int): List<Grupa>

     */

}