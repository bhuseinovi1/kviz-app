package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Kviz

@Dao
interface KvizDao {
    @Query("SELECT * FROM Kviz")
    suspend fun getAll(): List<Kviz>

    /*@Query("SELECT * FROM Kviz WHERE id=:id")
    suspend fun getGrupeById(id: Int): Kviz


     */
    @Query("SELECT * FROM Kviz WHERE id=:id")
    suspend fun getKvizById(id: Int): Kviz

    @Query("DELETE FROM Kviz")
    suspend fun deleteAllKvizovi()

    @Insert
    suspend fun insertAll(vararg kvizovi: Kviz)

    @Query("UPDATE Kviz SET predan='true' WHERE id=:kvizId")
    fun postaviPredan(kvizId: Int)
}