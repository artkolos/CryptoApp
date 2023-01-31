package com.example.cryptoapp2.pojo

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptoapp2.api.ApiFactory
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import convertTimeStampToTime

@Entity(tableName = "full_price_list")
data class CoinPriceInfo(
    @SerializedName("TYPE")
    @Expose
    val type: String? = null,

    @SerializedName("MARKET")
    @Expose
    val market: String? = null,

    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    @Expose
    val fromSymbol: String = "",

    @SerializedName("TOSYMBOL")
    @Expose
    val toSymbol: String? = null,

    @SerializedName("FLAGS")
    @Expose
    val flags: String? = null,

    @SerializedName("PRICE")
    @Expose
    val price: Double? = null,

    @SerializedName("LASTUPDATE")
    @Expose
    val lastUpdate: Long? = null,

    @SerializedName("MEDIAN")
    @Expose
    val median: Double? = null,

    @SerializedName("LASTVOLUME")
    @Expose
    val lastVolume: Double? = null,

    @SerializedName("LASTVOLUMETO")
    @Expose
    val lastVolumeTo: Double? = null,

    @SerializedName("LASTTRADEID")
    @Expose
    val lastTradeId: String? = null,

    @SerializedName("IMAGEURL")
    @Expose
    val imageUrl: String? = null,
){
    fun getFormattedTime(): String
    {
        return convertTimeStampToTime(lastUpdate)
    }

    fun getFullImageUrl(): String{
        return ApiFactory.BASE_IMAGE_URl + imageUrl
    }
}