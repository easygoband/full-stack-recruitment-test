package com.fernando.zssn.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernando.zssn.core.ApiClient
import com.fernando.zssn.data.model.Survivor
import kotlinx.coroutines.launch

class MainViewModel :  ViewModel(){
    val survivors = MutableLiveData<List<Survivor>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = ApiClient.survivorService.listAllSurvivors("infectedReports<3")

            survivors.postValue(response.data)
            isLoading.postValue(false)
        }
    }
}