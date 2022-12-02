package ba.etf.rma21.projekat.data

import android.content.Context
import androidx.room.*
import ba.etf.rma21.projekat.data.dao.*
import ba.etf.rma21.projekat.data.models.*

@Database(entities = arrayOf(Kviz::class, Pitanje::class,
    Odgovor::class, Grupa::class, Predmet::class,Account::class), version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun accountDao(): AccountDao
    abstract fun grupaDao(): GrupaDao
    abstract fun predmetDao(): PredmetDao
    abstract fun kvizDao(): KvizDao
    abstract fun pitanjeDao(): PitanjeDao
    abstract fun odgovorDao(): OdgovorDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private var converterInstance = Converters()
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }
        fun setInstance(appdb:AppDatabase):Unit{
            INSTANCE=appdb
        }
        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "RMA21DB"
            ).build()
    }
}