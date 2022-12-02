package ba.etf.rma21.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Odgovor(
        @PrimaryKey(autoGenerate = true) @SerializedName("id") var id: Int?,
        @ColumnInfo @SerializedName("odgovoreno") var odgovoreno: Int,
        @ColumnInfo @SerializedName("PitanjeId") var PitanjeId: Int,
        @ColumnInfo @SerializedName("KvizTakenId") var KvizTakenId: Int,
        @ColumnInfo @SerializedName("KvizId") var KvizId: Int
) {
    constructor(odgovoreno: Int,PitanjeId: Int,KvizTakenId: Int) : this (null,odgovoreno,PitanjeId,KvizTakenId,0)
}