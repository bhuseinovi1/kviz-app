package ba.etf.rma21.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Predmet(
        @PrimaryKey @SerializedName("id") var id: Int,
        @ColumnInfo @SerializedName("naziv") var naziv: String,
        @ColumnInfo @SerializedName("godina") var godina: Int
)