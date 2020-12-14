package com.example.mvvm_login_modelo.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.mvvm_login_modelo.BaseDatos.BaseDatosUser
import com.example.mvvm_login_modelo.Modelo.Person
import com.example.mvvm_login_modelo.Modelo.Repository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class  AppViewModel(application: Application) : AndroidViewModel(application), Repository.ResultLogin{

    private val myRepository: Repository


    init {
        var  personDao = BaseDatosUser.getDatabase(application).personDao()
         myRepository= Repository(personDao,this)
    }



    val resultado=MutableLiveData<String>()

    //TODO UNO MUTABLE Y OTRO FIJO
    private val _result= MutableLiveData<Boolean>()
    val result: LiveData<Boolean> get()=_result

    private val _election= MutableLiveData<String>()
    val election: LiveData<String> get()=_election

    private val _progreso = MutableLiveData<Boolean>()
    val progreso:LiveData<Boolean> get()=_progreso



    fun deleteUser() {
       myRepository.deleteOneUser()

    }

    fun restoreSession():LiveData<Person> {
        return myRepository.restoreSession()

    }



//******************************************************************************************************
    fun buttonRegisterClicked(email:String){

                        val user = Person(
                            email = email,
                            password = "password",
                            name = "User",
                            phone = "96000000"
                        )
                        myRepository.insertOneUser(user)

                        resultado.value=email


    }

//******************************************************************************************************
      fun loginClicked(email:String, pass:String){
        _progreso.value = true
        myRepository.validateUser(email, pass)

      }

    override fun updateUI(user: FirebaseUser?) {

        _result.value = user!=null
        _progreso.value = false

    }
//******************************************************************************************************

    fun  emailOfSession (email:String){
        _election.value=email

    }


}