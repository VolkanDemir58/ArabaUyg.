package com.example.volkandemirproje.model
import com.google.gson.annotations.SerializedName

data class Arabalar(
    @SerializedName("AdiSoyadi")
    val adiSoyadi: String?,
    @SerializedName("Eposta")
    val eposta: String?,
    @SerializedName("ResimURL")
    val resimURL: String?,
    @SerializedName("description")
    val description:String?,

)
