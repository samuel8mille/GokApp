package com.samuel.gokapp.model.gokrepository

import com.google.gson.annotations.SerializedName

data class DataModel(

    @SerializedName("spotlight")
    var spotlight: List<SpotlightModel>,

    @SerializedName("products")
    var products: List<ProductsModel>,

    @SerializedName("cash")
    var cash: CashModel
)
