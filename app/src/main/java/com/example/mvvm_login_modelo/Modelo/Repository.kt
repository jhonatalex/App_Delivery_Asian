package com.example.mvvm_login_modelo.Modelo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Delete
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository (private val myUserDao: PersonDao, private val callback: ResultLogin ) {

    private  var auth: FirebaseAuth = FirebaseAuth.getInstance()

     fun restoreSession():LiveData<Person>{
        return myUserDao.restoreSessionUser()

    }




    fun insertOneUser(user:Person) = CoroutineScope(Dispatchers.IO).launch{
        myUserDao.insertUser(user)

    }

    fun deleteOneUser() = CoroutineScope(Dispatchers.IO).launch{
        myUserDao.deleteAllUser()

    }


    fun validateUser(email:String,pass:String) {

        CoroutineScope(Dispatchers.IO).launch {
            Thread.sleep(3000)
            auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d("ESTATE GOOD", "LOGINWithEmail:success")
                        val user = auth.currentUser
                        callback.updateUI(user)

                    } else {
                        Log.e("ESTATE BAD", "LOGIN CON :failure", it.exception)
                        callback.updateUI(null)
                    }

                }

        }


    }


    interface  ResultLogin{
        fun updateUI(user: FirebaseUser?)

    }







}