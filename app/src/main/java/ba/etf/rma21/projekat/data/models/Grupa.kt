package ba.etf.rma21.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Grupa(
        @PrimaryKey @SerializedName("id") var id: Int,
        @ColumnInfo @SerializedName("naziv") var naziv: String,
        @ColumnInfo @SerializedName("PredmetId") var PredmetId: Int
        //@SerializedName("KvizoviGrupe") var KvizoviGrupe: Kviz
)