package com.example.cryptoapp2.pojo

import com.example.cryptoapp2.pojo.CoinInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Datum(
    @SerializedName("CoinInfo")
    @Expose
    val coinInfo: CoinInfo? = null
)