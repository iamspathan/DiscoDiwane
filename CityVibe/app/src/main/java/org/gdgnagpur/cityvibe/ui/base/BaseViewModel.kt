package org.gdgnagpur.cityvibe.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel(){

    val errorLiveData : MutableLiveData<String> = MutableLiveData()

    fun setError(error: String){
        errorLiveData.postValue(error)
    }
    enum class STATE {
        SUCCESS,LOADING,ERROR
    }

}