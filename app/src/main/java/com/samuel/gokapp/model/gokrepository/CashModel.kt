package com.samuel.gokapp.model.gokrepository

import com.google.gson.annotations.SerializedName

data class CashModel(

    @SerializedName("title")
    var title: String,

    @SerializedName("bannerURL")
    var bannerURL: String,

    @SerializedName("description")
    var description: String
)