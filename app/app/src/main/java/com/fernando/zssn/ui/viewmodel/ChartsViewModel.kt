package com.fernando.zssn.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernando.zssn.core.ApiClient
import com.fernando.zssn.data.network.response.ResponseItemList
import com.fernando.zssn.data.network.response.ResponseList
import kotlinx.coroutines.launch

class ChartsViewModel : ViewModel(){
    val survivorsResponse = MutableLiveData<ResponseList>()
    val itemsResponse = MutableLiveData<ResponseItemList>()
    val isLoading = MutableLiveData<Boolean>()

    fun getAllSurvivors() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = ApiClient.survivorService.listAllSurvivors(null)

            survivorsResponse.postValue(response)
            isLoading.postValue(false)
        }
    }

    fun getAllItems() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = ApiClient.itemService.fetchItems()

            itemsResponse.postValue(response)
            isLoading.postValue(false)
        }
    }
}