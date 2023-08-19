package br.com.esperandio.exchangemycash.viewmodel

import android.content.Intent
import androidx.lifecycle.ViewModel
import br.com.esperandio.exchangemycash.model.RatesData
import br.com.esperandio.exchangemycash.model.Repository
import br.com.esperandio.exchangemycash.view.etStartMoney
import br.com.esperandio.exchangemycash.view.spinnerFrom
import br.com.esperandio.exchangemycash.view.spinnerTo
import br.com.esperandio.exchangemycash.view.tvResultMoney
import java.math.RoundingMode

class MainViewModel  : ViewModel() {

    var repository = Repository()


    suspend fun getApiResult(): RatesData {
        return repository.getApiResult()
    }


    fun result(fromValue: Double, toValue: Double, marketRate: Double): String {
        var result: String = ""
        if (etStartMoney.text.isNullOrBlank() || etStartMoney.text.isEmpty()) {
            result = "0.0"
        } else {
            var totalFinal =
                calc(fromValue, toValue, etStartMoney.text.toString().toDouble())?.times(marketRate)
            var df = totalFinal?.toBigDecimal()?.setScale(2, RoundingMode.HALF_EVEN)
            result = df.toString()
        }
        return result
    }

    private fun calc(fromValue: Double, toValue: Double, startValue: Double): Double? {
        var finalValue = 0.0
        if (!fromValue.equals(0.0)) {
            finalValue = (startValue * toValue) / fromValue
        }
        return finalValue
    }


    fun share() : Intent{

        var intent = Intent().apply {
            this.action = Intent.ACTION_SEND
            this.putExtra(Intent.EXTRA_TEXT, etStartMoney.text.toString() + " " +  spinnerFrom.selectedItem.toString() +  " = "+ tvResultMoney.text.toString() + " " + spinnerTo.selectedItem.toString())
            this.type = "text/plain"
        }
        return intent
    }

}