package com.fernando.zssn.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fernando.zssn.core.ApiClient
import com.fernando.zssn.data.model.Survivor
import com.fernando.zssn.data.network.response.ResponseSingle
import kotlinx.coroutines.launch

class ProfileViewModel(private val survivorId: Int) : ViewModel() {
    val survivor = MutableLiveData<Survivor>()
    val survivorReportedResponse = MutableLiveData<ResponseSingle>()
    val isLoading = MutableLiveData<Boolean>()

    fun getSingleSurvivor() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = ApiClient.survivorService.fetchSingleSurvivor(survivorId)

            survivor.postValue(response.data)
            isLoading.postValue(false)
        }
    }

    fun reportSurvivor() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = ApiClient.survivorService.reportSurvivor(survivorId)

            survivorReportedResponse.postValue(response)
            isLoading.postValue(false)
        }
    }

}

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(private val survivorId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(survivorId) as T
    }
}