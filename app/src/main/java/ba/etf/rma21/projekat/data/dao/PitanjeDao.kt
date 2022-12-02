package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.Pitanje

@Dao
interface PitanjeDao {
    @Query("SELECT * FROM Pitanje")
    suspend fun getAll(): List<Pitanje>

    @Query("SELECT * FROM Pitanje WHERE id=:id")
    suspend fun getPitanjeById(id: Int): Pitanje

    @Query("SELECT * FROM Pitanje WHERE kvizId=:kvizId")
    suspend fun getPitanjaByKviz(kvizId: Int): List<Pitanje>

    @Query("DELETE FROM Pitanje")
    suspend fun deleteAllPitanja()

    @Insert
    suspend fun insertAll(vararg pitanja: Pitanje)
}