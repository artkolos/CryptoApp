package com.example.cryptoapp2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cryptoapp2.adapter.CoinInfoAdapter
import com.example.cryptoapp2.pojo.CoinPriceInfo
import kotlinx.android.synthetic.main.activity_coin_price_list.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CoinViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                val intent =
                    CoinDetailActivity.newIntent(this@MainActivity, coinPriceInfo.fromSymbol)
                startActivity(intent)
            }
        }
        rcCoinPriceList.adapter = adapter
        viewModel = ViewModelProviders.of(this)[CoinViewModel::class.java]
        viewModel.priceList.observe(this, Observer {
            adapter.coinInfoList = it
        })
    }
}