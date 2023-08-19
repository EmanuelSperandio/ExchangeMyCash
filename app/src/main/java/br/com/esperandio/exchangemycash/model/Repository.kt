package br.com.esperandio.exchangemycash.model

class Repository {

    suspend fun getApiResult(): RatesData {
        return RetrofitInstance.api.getRates()
    }


}