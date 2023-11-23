package com.example.volkandemirproje.util

import android.content.Context
import android.net.ConnectivityManager


object NetworkUtil {
    fun isInternetAvaible(context: Context):Boolean{
        val connectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }

}