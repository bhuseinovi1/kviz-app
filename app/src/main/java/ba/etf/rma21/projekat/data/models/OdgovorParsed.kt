package ba.etf.rma21.projekat.data.models

import com.google.gson.annotations.SerializedName

data class OdgovorParsed(
    @SerializedName("odgovor") var odgovor: Int,
    @SerializedName("pitanje") var pitanje: Int,
    @SerializedName("bodovi") var bodovi: Int
)