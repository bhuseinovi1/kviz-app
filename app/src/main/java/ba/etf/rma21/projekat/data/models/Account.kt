package ba.etf.rma21.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Account(
        @PrimaryKey @SerializedName("id") var id: Int,
        @ColumnInfo @SerializedName("student") var student: String,
        @ColumnInfo @SerializedName("acHash") var acHash: String,
        @ColumnInfo @SerializedName("lastUpdate") var lastUpdate: String
)