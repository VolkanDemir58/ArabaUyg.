package com.example.volkandemirproje.network.hoca

import com.example.volkandemirproje.model.Arabalar
import retrofit2.http.GET
import retrofit2.http.Path

interface ArabaApi {

    @GET("hocalar.jsonl")
    suspend fun getHocalar(): List<Arabalar>

    @GET("hocalar.jsonl/{hocaIdDegeri}")
    suspend fun getHoca(@Path("hocaIdDegeri")hocaId:Int):Arabalar
}