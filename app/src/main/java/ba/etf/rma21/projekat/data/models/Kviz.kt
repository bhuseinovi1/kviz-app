package ba.etf.rma21.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Kviz (
        @PrimaryKey @SerializedName("id") var id: Int,
        @ColumnInfo @SerializedName("naziv") var naziv: String,
        @ColumnInfo @SerializedName("datumPocetak") var datumPocetka: String,
        @ColumnInfo @SerializedName("datumKraj") var datumKraj: String,
        @ColumnInfo @SerializedName("trajanje") var trajanje: Int,

        @ColumnInfo @SerializedName("predan") var predan: String = "false"
)