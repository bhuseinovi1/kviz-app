package ba.etf.rma21.projekat.data.repositories

import java.net.URL

class ApiConfig {
    companion object {
        var baseURL: String = "https://rma21-etf.herokuapp.com"
        fun postaviBaseURL(baseUrl: String):Unit {
            this.baseURL = baseUrl
        }
    }
}