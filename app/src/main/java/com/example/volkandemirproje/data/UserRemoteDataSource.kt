package com.example.volkandemirproje.data

import com.example.volkandemirproje.model.Arabalar
import com.example.volkandemirproje.network.hoca.ArabaService
import com.example.volkandemirproje.network.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow



class UserRemoteDataSource : UserDataSource {

    override  fun getAllUsers(): Flow<Resource<List<Arabalar>>> = flow {


        try {
            emit(Resource.Loading())
            val hocalarList = ArabaService.build().getHocalar()
            emit(Resource.Success(hocalarList))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }

    override fun getUser(id: Int): Flow<Resource<Arabalar>> = flow {

        try {
            emit(Resource.Loading())
            val donenData = ArabaService.build().getHoca(id)
            emit(Resource.Success(donenData))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }
}