package com.example.volkandemirproje.network.hoca


import com.example.volkandemirproje.util.Constans
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ArabaService {


    fun build(): ArabaApi {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHtppClient =  OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constans.BASE_URL_HOCA_SERVICE)
            .client(okHtppClient)
            .build()

        return retrofit.create(ArabaApi::class.java)
    }
}