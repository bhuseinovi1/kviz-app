package ba.etf.rma21.projekat.data.models

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Changed(
        @SerializedName("changed") var changed: Boolean
)