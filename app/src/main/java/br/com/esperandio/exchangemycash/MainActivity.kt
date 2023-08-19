package br.com.esperandio.exchangemycash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.widget.doOnTextChanged
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.math.RoundingMode

lateinit var tvResultMoney : TextView
lateinit var etStartMoney : EditText
lateinit var spinnerTo : Spinner
lateinit var spinnerFrom : Spinner
lateinit var apiResult : RatesData

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        spinnerTo = findViewById(R.id.spinner_to)
        etStartMoney = findViewById(R.id.et_mystartvalue)
        tvResultMoney = findViewById(R.id.tv_result)
        spinnerFrom = findViewById(R.id.spinner_from)

        GlobalScope.launch {
            apiResult = RetrofitInstance.api.getRates()

            spinnerFrom.onItemSelectedListener = this@MainActivity
            spinnerTo.onItemSelectedListener = this@MainActivity

            etStartMoney.doOnTextChanged { text, start, before, count ->

                var fromValue : Double = 0.0
                var toValue: Double = 0.0

                when(spinnerFrom.selectedItem){
                    "Australian Dollar"-> {fromValue = apiResult.rates.AUD}
                    "Real"-> {fromValue = apiResult.rates.BRL}
                    "Canadian Dollar"-> {fromValue = apiResult.rates.CAD}
                    "Swiss Franc"-> {fromValue = apiResult.rates.CHF}
                    "人民币"-> {fromValue = apiResult.rates.CNY}
                    "Euro"-> {fromValue = apiResult.rates.EUR}
                    "Pound Sterling"-> {fromValue = apiResult.rates.GBP}
                    "港幣"-> {fromValue = apiResult.rates.HKD}
                    "円"-> {fromValue = apiResult.rates.JPY}
                    "New Zealand Dollar"-> {fromValue = apiResult.rates.NZD}
                    "United States Dollar"-> {fromValue = apiResult.rates.USD}
                }

                when(spinnerTo.selectedItem){
                    "Australian Dollar"-> {toValue = apiResult.rates.AUD}
                    "Real"-> {toValue = apiResult.rates.BRL}
                    "Canadian Dollar"-> {toValue = apiResult.rates.CAD}
                    "Swiss Franc"-> {toValue = apiResult.rates.CHF}
                    "人民币"-> {toValue = apiResult.rates.CNY}
                    "Euro"-> {toValue = apiResult.rates.EUR}
                    "Pound Sterling"-> {toValue = apiResult.rates.GBP}
                    "港幣"-> {toValue = apiResult.rates.HKD}
                    "円"-> {toValue = apiResult.rates.JPY}
                    "New Zealand Dollar"-> {toValue = apiResult.rates.NZD}
                    "United States Dollar"-> {toValue = apiResult.rates.USD}
                }

                result(fromValue,toValue)


            }
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        var fromValue : Double = 0.0
        var toValue: Double = 0.0

        when(spinnerFrom.selectedItem){
            "Australian Dollar"-> {fromValue = apiResult.rates.AUD}
            "Real"-> {fromValue = apiResult.rates.BRL}
            "Canadian Dollar"-> {fromValue = apiResult.rates.CAD}
            "Swiss Franc"-> {fromValue = apiResult.rates.CHF}
            "人民币"-> {fromValue = apiResult.rates.CNY}
            "Euro"-> {fromValue = apiResult.rates.EUR}
            "Pound Sterling"-> {fromValue = apiResult.rates.GBP}
            "港幣"-> {fromValue = apiResult.rates.HKD}
            "円"-> {fromValue = apiResult.rates.JPY}
            "New Zealand Dollar"-> {fromValue = apiResult.rates.NZD}
            "United States Dollar"-> {fromValue = apiResult.rates.USD}
        }

        when(spinnerTo.selectedItem){
            "Australian Dollar"-> {toValue = apiResult.rates.AUD}
            "Real"-> {toValue = apiResult.rates.BRL}
            "Canadian Dollar"-> {toValue = apiResult.rates.CAD}
            "Swiss Franc"-> {toValue = apiResult.rates.CHF}
            "人民币"-> {toValue = apiResult.rates.CNY}
            "Euro"-> {toValue = apiResult.rates.EUR}
            "Pound Sterling"-> {toValue = apiResult.rates.GBP}
            "港幣"-> {toValue = apiResult.rates.HKD}
            "円"-> {toValue = apiResult.rates.JPY}
            "New Zealand Dollar"-> {toValue = apiResult.rates.NZD}
            "United States Dollar"-> {toValue = apiResult.rates.USD}
        }

        result(fromValue,toValue)

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        tvResultMoney.text = "0.0"
    }

    private fun result(fromValue: Double, toValue : Double){
        if(etStartMoney.text.isNullOrBlank() || etStartMoney.text.isEmpty()) {
            tvResultMoney.text = "0.0"
        }else{
            var totalFinal = calc(fromValue, toValue,etStartMoney.text.toString().toDouble())
            var df = totalFinal?.toBigDecimal()?.setScale(2, RoundingMode.HALF_EVEN)
            tvResultMoney.text = df.toString()
        }
    }

    private fun calc(fromValue : Double, toValue : Double, startValue : Double) : Double? {
        var finalValue = 0.0
        if(!fromValue.equals(0.0)){
            finalValue = (startValue * toValue) / fromValue
        }
        return finalValue
    }

}