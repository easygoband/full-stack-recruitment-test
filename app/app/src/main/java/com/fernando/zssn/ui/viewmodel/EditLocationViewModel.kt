package com.fernando.zssn.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernando.zssn.core.ApiClient
import com.fernando.zssn.data.network.request.LocationRequest
import com.fernando.zssn.data.network.response.ResponseSingle
import kotlinx.coroutines.launch

class EditLocationViewModel() : ViewModel() {
    val locationResponse = MutableLiveData<ResponseSingle>()
    val survivorResponse = MutableLiveData<ResponseSingle>()
    val isLoading = MutableLiveData<Boolean>()

    fun updateLocation(locationRequest: LocationRequest, survivorId: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = ApiClient.survivorService.updateSurvivorLocation(survivorId, locationRequest)

            locationResponse.postValue(response)
            isLoading.postValue(false)
        }
    }

    fun getSurvivor(survivorId: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = ApiClient.survivorService.fetchSingleSurvivor(survivorId)

            survivorResponse.postValue(response)
            isLoading.postValue(false)
        }
    }
}
