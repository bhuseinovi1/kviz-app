package ba.etf.rma21.projekat.data.models

import androidx.room.*
import ba.etf.rma21.projekat.data.Converters
import com.google.gson.annotations.SerializedName

@Entity
data class Pitanje(
        @PrimaryKey @SerializedName("id") var id: Int,
        @ColumnInfo @SerializedName("naziv") var naziv: String,
        @ColumnInfo @SerializedName("tekstPitanja") var tekstPitanja: String,
        @TypeConverters(Converters::class) @ColumnInfo @SerializedName("opcije") var opcije: List<String>,
        @ColumnInfo @SerializedName("tacan") var tacan: Int,
        @ColumnInfo @SerializedName("KvizId") var kvizId: Int
)