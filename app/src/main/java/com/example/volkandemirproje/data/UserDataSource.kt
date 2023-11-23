package com.example.volkandemirproje.data

import com.example.volkandemirproje.model.Arabalar
import com.example.volkandemirproje.network.resource.Resource
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    fun getAllUsers(): Flow<Resource<List<Arabalar>>>

    fun getUser(id:Int):Flow<Resource<Arabalar>>

}