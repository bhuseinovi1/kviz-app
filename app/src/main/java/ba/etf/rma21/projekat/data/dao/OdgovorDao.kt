package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Account
import ba.etf.rma21.projekat.data.models.Odgovor
import ba.etf.rma21.projekat.data.models.Pitanje

@Dao
interface OdgovorDao {
    @Query("SELECT * FROM Odgovor")
    suspend fun getAll(): List<Odgovor>

    @Query("SELECT * FROM Odgovor WHERE id=:id")
    suspend fun getOdgovorById(id: Int): Odgovor

    @Query("SELECT * FROM Odgovor WHERE PitanjeId=:pitanjeId AND KvizTakenId=:kvizTakenId")
    suspend fun getOdgovorByPitanjeAndKvizTaken(pitanjeId: Int,kvizTakenId: Int): List<Odgovor>

    @Query("DELETE FROM Odgovor")
    suspend fun deleteAllOdgovori()

    @Insert
    suspend fun insertAll(vararg odgovors: Odgovor)

    @Query("SELECT * FROM Odgovor WHERE KvizId=:kvizId")
    suspend fun getOdgovoreZaKviz(kvizId: Int): List<Odgovor>

    @Query("SELECT * FROM Odgovor WHERE KvizTakenId=:kvizTakenId")
    suspend fun getForKvizTaken(kvizTakenId: Int): List<Odgovor>

    @Query("UPDATE Odgovor SET KvizId =:kvizId WHERE KvizTakenId=:idPokusaja AND PitanjeId=:pitanjeId")
    suspend fun updateujOdgovor(kvizId: Int,idPokusaja: Int, pitanjeId: Int)

    @Query("SELECT * FROM Odgovor WHERE KvizId=:kvizId")
    suspend fun getForKviz(kvizId: Int): List<Odgovor>
}