package com.example.volkandemirproje.ui.hocalar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.volkandemirproje.data.UserRepository
import com.example.volkandemirproje.model.Arabalar
import com.example.volkandemirproje.network.resource.ResourceStatus
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val userRepository: UserRepository = UserRepository()
    var allUsersLiveData = MutableLiveData<List<Arabalar?>>()
    var allUserLiveData = MutableLiveData<Arabalar?>()
    var error = MutableLiveData<Throwable>()
    var loading = MutableLiveData<Boolean>()


    fun getAllUsers() {
        viewModelScope.launch {

            userRepository.getAllUsers().collect {
                when (it.status) {
                    ResourceStatus.LOADING -> {
                        loading.value = true

                    }
                    ResourceStatus.ERROR -> {
                        loading.value = false
                        error.value = it.error
                    }
                    ResourceStatus.SUCCESS -> {
                        loading.value = false
                        allUsersLiveData.value = it.succesData
                    }

                }
            }
        }
    }

    fun getUser(id:Int) {
        viewModelScope.launch {

            userRepository.getUser(id).collect {
                when (it.status) {
                    ResourceStatus.LOADING -> {
                        loading.value = true

                    }
                    ResourceStatus.ERROR -> {
                        loading.value = false
                        error.value = it.error
                    }
                    ResourceStatus.SUCCESS -> {
                        loading.value = false
                        allUserLiveData.value = it.succesData
                    }

                }
            }
        }
    }

}