package ba.etf.rma21.projekat.data.models

import com.google.gson.annotations.SerializedName

data class ResponseMessage(
        @SerializedName("message") var message: String
)