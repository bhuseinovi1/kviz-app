package ba.etf.rma21.projekat.data.models

import com.google.gson.annotations.SerializedName

data class KvizTaken(
        @SerializedName("id") var id: Int,
        @SerializedName("student") var student: String,
        @SerializedName("osvojeniBodovi") var osvojeniBodovi: Int,
        @SerializedName("datumRada") var datumRada: String,
        @SerializedName("KvizId") var KvizId: Int
)