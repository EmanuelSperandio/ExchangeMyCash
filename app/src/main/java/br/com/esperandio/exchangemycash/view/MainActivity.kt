package br.com.esperandio.exchangemycash.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.widget.doOnTextChanged
import br.com.esperandio.exchangemycash.R
import br.com.esperandio.exchangemycash.model.RatesData
import br.com.esperandio.exchangemycash.viewmodel.MainViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

lateinit var tvResultMoney : TextView
lateinit var etStartMoney : EditText
lateinit var spinnerTo : Spinner
lateinit var spinnerFrom : Spinner
lateinit var apiResult : RatesData
lateinit var buttonShare : Button
lateinit var spinnerMarketRate : Spinner

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener  {

    var viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)


        spinnerTo = findViewById(R.id.spinner_to)
        etStartMoney = findViewById(R.id.et_mystartvalue)
        tvResultMoney = findViewById(R.id.tv_result)
        spinnerFrom = findViewById(R.id.spinner_from)
        buttonShare = findViewById(R.id.bShare)
        spinnerMarketRate = findViewById(R.id.spinner_MarketRate)

        buttonShare.setOnClickListener {
            startActivity(viewModel.share())
        }

        GlobalScope.launch {

            apiResult = viewModel.getApiResult()

            spinnerFrom.onItemSelectedListener = this@MainActivity
            spinnerTo.onItemSelectedListener = this@MainActivity
            spinnerMarketRate.onItemSelectedListener = this@MainActivity

            etStartMoney.doOnTextChanged { text, start, before, count ->

                var fromValue : Double = 0.0
                var toValue: Double = 0.0
                var marketRate : Double = 0.0

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

                when(spinnerMarketRate.selectedItem){
                    "0.0% market rate" ->{marketRate = 1.0}
                    "0.5% market rate" ->{marketRate = 0.995}
                    "1.0% market rate" ->{marketRate = 0.99}
                    "1.5% market rate" ->{marketRate = 0.985}
                    "2.0% market rate" ->{marketRate = 0.98}
                }

                tvResultMoney.text = viewModel.result(fromValue,toValue, marketRate)

            }
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        var fromValue : Double = 0.0
        var toValue: Double = 0.0
        var marketRate : Double = 0.0

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

        when(spinnerMarketRate.selectedItem){
            "0.0% market rate" ->{marketRate = 1.0}
            "0.5% market rate" ->{marketRate = 0.995}
            "1.0% market rate" ->{marketRate = 0.99}
            "1.5% market rate" ->{marketRate = 0.985}
            "2.0% market rate" ->{marketRate = 0.98}
        }

        tvResultMoney.text = viewModel.result(fromValue,toValue, marketRate)

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        tvResultMoney.text = "0.0"
    }


}