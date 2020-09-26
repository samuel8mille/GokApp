package com.samuel.gokapp.model.gokrepository

import com.google.gson.annotations.SerializedName

data class ProductsModel(

    @SerializedName("name")
    var name: String,

    @SerializedName("imageURL")
    var imageURL: String,

    @SerializedName("description")
    var description: String
)