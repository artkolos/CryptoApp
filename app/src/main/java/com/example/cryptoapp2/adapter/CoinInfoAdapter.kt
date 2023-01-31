package com.example.cryptoapp2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cryptoapp2.R
import com.example.cryptoapp2.pojo.CoinInfo
import com.example.cryptoapp2.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin_info.view.*

class CoinInfoAdapter(private val context: Context): RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>(){

    var coinInfoList: List<CoinPriceInfo> = listOf()
        set(value){
            field = value
            notifyDataSetChanged()
        }
    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) = with(holder) {
        val coinInfo = coinInfoList[position]
        val symbolsTemplate = context.resources.getString(R.string.symbols_template)
        val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
        tvSymbols.text = String.format(symbolsTemplate,coinInfo.fromSymbol, coinInfo.toSymbol)
        tvPrice.text = "${coinInfo.price}"
        tvUpdateTime.text = String.format(lastUpdateTemplate, coinInfo.getFormattedTime())
        Picasso.get().load(coinInfo.getFullImageUrl()).into(ivLogoCoin)
        itemView.setOnClickListener{
            onCoinClickListener?.onCoinClick(coinInfo)
        }
    }

    override fun getItemCount() = coinInfoList.size

    inner class CoinInfoViewHolder(item: View): RecyclerView.ViewHolder(item){
        val ivLogoCoin = itemView.ivLogoCoin
        val tvSymbols = itemView.tvSymbols
        val tvPrice = itemView.tvPrice
        val tvUpdateTime = itemView.tvUpdateTime
    }

    interface OnCoinClickListener{
        fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }
}