package ba.etf.rma21.projekat.data.api

import ba.etf.rma21.projekat.data.models.*
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.KvizTaken
import ba.etf.rma21.projekat.data.models.Odgovor
import ba.etf.rma21.projekat.data.models.Pitanje
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface Api {
    // predmet

    @GET("/predmet")
    suspend fun getPredmeti(): Response<List<Predmet>>

    @GET("/predmet/{id}")
    suspend fun getPredmetById(@Path("id") predmetId: Int): Response<Predmet>


    // grupa

    @GET("/kviz/{id}/grupa")
    suspend fun getGrupeZaKviz(@Path("id") kvizId: Int): Response<List<Grupa>>

    @POST("/grupa/{gid}/student/{id}")
    suspend fun dodajUGrupuSaId(@Path("gid") grupaId: Int, @Path("id") studentId: String): Response<ResponseMessage>

    @GET("/student/{id}/grupa")
    suspend fun getGrupeZaStudenta(@Path("id") studentId: String): Response<List<Grupa>>

    @GET("/grupa")
    suspend fun getGrupe(): Response<List<Grupa>>

    @GET("/grupa/{id}")
    suspend fun getGrupaById(@Path("id") grupaId: Int): Response<Grupa>

    @GET("/predmet/{id}/grupa")
    suspend fun getGrupeZaPredmet(@Path("id") predmetId: Int): Response<List<Grupa>>


    //kviz

    @GET("/kviz")
    suspend fun getKvizovi(): Response<List<Kviz>>

    @POST("/kviz/{id}")
    suspend fun getKvizById(@Path("id") kvizId: Int): Response<Kviz>

    @GET("/grupa/{id}/kvizovi")
    suspend fun getKvizoviZaGrupu(@Path("id") grupaId : Int): Response<List<Kviz>>


    //odgovor

    @GET("/student/{id}/kviztaken/{ktid}/odgovori")
    suspend fun getOdgovoriZaKvizTakeniStudenta(@Path("id") studentId: String,
                                                @Path("ktid") kvizTakenaId: Int): Response<List<Odgovor>>

    @POST("/student/{id}/kviztaken/{ktid}/odgovor")
    suspend fun dodajOdgovorZaKvizTakeniStudenta(@Path("id") studentId: String,
                                                 @Path("ktid") kvizTakenId: Int,
                                                 @Body body: OdgovorParsed): Unit


    // kviztaken

    @GET("/student/{id}/kviztaken")
    suspend fun getPokusajiZaStudenta(@Path("id") studentId : String): Response<List<KvizTaken>>

    @POST("/student/{id}/kviz/{kid}")
    suspend fun zapocniKvizZaStudenta(@Path("id") studentId: String,
                                      @Path("kid") kvizTakenId: Int): Response<KvizTaken>


    // account
    @GET("/student/{id}")
    suspend fun getStudentById(@Path("id") studentId : String): Response<Account>

    @DELETE("/student/{id}/upisugrupeipokusaji")
    suspend fun obrisiPodatkeZaStudenta(@Path("id") studentId : String): Response<ResponseMessage>

    @GET("/account/{id}/lastUpdate")
    suspend fun biloPromjena(@Path("id") id:String,@Query("date") date:String

    ): Response<Changed>

    // pitanje

    @GET("/kviz/{id}/pitanja")
    suspend fun getPitanjaZaKviz(@Path("id") kvizId: Int

    ): Response<List<Pitanje>>
}