package com.example.cryptoapp2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_coin_detail.*

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        viewModel = ViewModelProviders.of(this)[CoinViewModel::class.java]
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        if (fromSymbol != null) {
            viewModel.getDetailInfo(fromSymbol).observe(this, Observer {
                    textViewPrice.text = "Price: ${it.price}"
                    textViewMinDay.text = "Минимум за день: ${it.price}"
                    textViewMaxDay.text = "Максимум за день: ${it.price}"
                    textViewLastDeal.text = "Последняя сделка: Bitfinex"
                    textViewUpdate.text = "Обновлено: ${it.getFormattedTime()}"
                    Picasso.get().load(it.getFullImageUrl()).into(ivLogoCoin)
                    textToSymbols.text = it.toSymbol
                    textViewFromSymbols.text = it.fromSymbol
                })
            }
    }

    companion object{
        private const val EXTRA_FROM_SYMBOL = "fSym"
        fun newIntent(context: Context, fSym: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fSym)
            return intent
        }
    }
}