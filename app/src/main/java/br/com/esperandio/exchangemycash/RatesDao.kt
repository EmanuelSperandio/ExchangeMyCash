package br.com.esperandio.exchangemycash

import retrofit2.http.GET

interface RatesDao {

    @GET("latest?access_key=c8c5a79979b8baf271473be192995b51")
    suspend fun getRates() : RatesData

}