package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Account
import ba.etf.rma21.projekat.data.models.Grupa

@Dao
interface AccountDao {

    @Query("SELECT * FROM Account")
    suspend fun getAll(): List<Account>

    @Query("UPDATE Account SET lastUpdate=:lastUpdate WHERE 1=1")
    suspend fun updatujLastUpdate(lastUpdate: String)

    @Query("SELECT lastUpdate FROM Account WHERE 1=1")
    suspend fun getLastUpdate(): String

    @Insert
    suspend fun insertAll(vararg accounts: Account)

    @Query("UPDATE Account SET acHash = :acHash WHERE 1=1")
    suspend fun updatujAcc(acHash: String)

    @Query("SELECT COUNT(*) AS broj_redova FROM Account")
    suspend fun getBrojRedovaAcc(): Int

    @Query("SELECT acHash FROM Account WHERE 1=1")
    suspend fun getHash(): String
}