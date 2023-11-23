package com.example.volkandemirproje.network.resource

sealed class Resource<T>(
    val succesData: T?,
    val error: Throwable?,
    val status: ResourceStatus
) {
    class Loading<T> : Resource<T>(null, null, ResourceStatus.LOADING)
    class Success<T>(succesData: T?) : Resource<T>(succesData, null, ResourceStatus.SUCCESS)
    class Error<T>(error: Exception) : Resource<T>(null, error, ResourceStatus.ERROR)
}