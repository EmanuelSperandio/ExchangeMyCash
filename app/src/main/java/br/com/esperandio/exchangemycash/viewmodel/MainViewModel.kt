package br.com.esperandio.exchangemycash.viewmodel

import androidx.lifecycle.ViewModel
import br.com.esperandio.exchangemycash.etStartMoney
import java.math.RoundingMode

class MainViewModel  : ViewModel() {


    private fun result(fromValue: Double, toValue: Double, marketRate: Double): String {
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


}