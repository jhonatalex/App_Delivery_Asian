package com.example.mvvm_login_modelo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_login_modelo.Modelo.MainViewModel
import com.google.firebase.analytics.FirebaseAnalytics


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    lateinit var texBox: EditText
    lateinit var progressbar: ProgressBar

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        
        //EVENTO PAR SABER CUANDO SE INICIA UNA APP
        val analytics:FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val box=Bundle()
        box.putString("msg","Ha instalado la APlicacion")
        analytics.logEvent("Iniciar",box)


        texBox = findViewById<EditText>(R.id.inputEmail)
        val passwor = findViewById<EditText>(R.id.inputPassword)
        val buttonIngresar = findViewById<Button>(R.id.btnLogin)
        progressbar = findViewById<ProgressBar>(R.id.progressBar)


        //OBSERVADOR LIVEDATA
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        //METODO1 QUE OBSERVA CUANDO SE CAMBIA EL VALOR EN MAINVIEWMODEL CAMBIA ACA
        viewModel.progreso.observe(this, Observer {
            progressbar.visibility = if (it) View.VISIBLE else View.GONE
        })
        //METODO2 QUE OBSERVA CUANDO SE CAMBIA EL VALOR EN MAINVIEWMODEL CAMBIA ACA
        viewModel.message.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })


        buttonIngresar.setOnClickListener {
            viewModel.buttonClicked(texBox.text.toString(), passwor.text.toString())

        }
    }
}



