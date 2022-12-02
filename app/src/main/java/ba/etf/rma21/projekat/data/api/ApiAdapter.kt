package ba.etf.rma21.projekat.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// singlton objekat kojisadrži instancu retrofita
object ApiAdapter {
    val retrofit : Api = Retrofit.Builder()
            .baseUrl("http://rma21-etf.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
}