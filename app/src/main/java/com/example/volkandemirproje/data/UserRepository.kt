package com.example.volkandemirproje.data

import com.example.volkandemirproje.model.Arabalar
import com.example.volkandemirproje.network.resource.Resource
import kotlinx.coroutines.flow.Flow

class UserRepository {
    private var userDataSource: UserDataSource? = null
    init { userDataSource = UserRemoteDataSource() }

    fun getAllUsers(): Flow<Resource<List<Arabalar>>> {
        return userDataSource!!.getAllUsers()
    }

    fun getUser(id : Int): Flow<Resource<Arabalar>> {
        return userDataSource!!.getUser(id)
    }
}