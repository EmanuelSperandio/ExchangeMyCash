package br.com.esperandio.exchangemycash.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api : RatesDao by lazy {
        Retrofit.Builder()
            .baseUrl("http://data.fixer.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RatesDao::class.java)
    }

}