package com.example.volkandemirproje.util

import com.google.gson.Gson


object ObjectUtils {


    fun <T> fromJson(json: String?, classOfT: Class<T>?): T {
        return Gson().fromJson(json, classOfT)
    }


    fun toJson(classA: Any): String {
        return Gson().toJson(classA)
    }


}