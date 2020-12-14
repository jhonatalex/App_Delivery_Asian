package com.example.mvvm_login_modelo.Modelo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel(){

    //TODO UNO MUTABLE Y OTRO FIJO
    private val _progreso = MutableLiveData<Boolean>()
    val progreso:LiveData<Boolean> get()=_progreso


    //TODO UNO MUTABLE Y OTRO FIJO
    private val _message= MutableLiveData<String>()
    val message:LiveData<String> get()=_message


    fun buttonClicked(user:String, pass:String){
        viewModelScope.launch{
            _progreso.value=true
            _message.value= withContext(Dispatchers.IO){
                Thread.sleep(3000)
                if (user.isNotEmpty() && pass.isNotEmpty()) " REGISTRO EXITOSO" else "ERROR LOGIN"
            }
            _progreso.value=false
        }



    }


}