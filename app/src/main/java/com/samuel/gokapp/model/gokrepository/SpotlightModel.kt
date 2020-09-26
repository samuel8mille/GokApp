package com.samuel.gokapp.model.gokrepository

import com.google.gson.annotations.SerializedName

data class SpotlightModel(

    @SerializedName("name")
    var name: String,

    @SerializedName("bannerURL")
    var bannerURL: String,

    @SerializedName("description")
    var description: String
)