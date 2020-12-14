package com.example.mvvm_login_modelo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mvvm_login_modelo.ViewModel.AppViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_second.*


class SecondFragment : Fragment() {

    private val viewModel: AppViewModel by activityViewModels()
    private var Bandera:Boolean=false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        button_login.setOnClickListener {

            if (inputEmail.text.isEmpty()||inputPassword.text.isEmpty() ){
                 Toast.makeText(context, "RELLENE TODOS LOS CAMPOS ", Toast.LENGTH_LONG).show()
            } else {
                val emailUser=inputEmail?.text.toString()
                val passUser=inputPassword?.text.toString()



               viewModel.loginClicked(emailUser, passUser)

                //PROGRESSBAR
                viewModel.progreso.observe(viewLifecycleOwner, Observer {

                    progressBar3.visibility = if (it) View.VISIBLE else View.GONE
                })


            }



            }

        // OBSERVA CUANDO SE CAMBIA EL VALOR EN VIEWMODEL CAMBIA ACA
        viewModel.result.observe(viewLifecycleOwner, Observer {


            if (it) {
            Toast.makeText(context, "Authentication Exitosa.", Toast.LENGTH_SHORT).show()
                    if (inputEmail.text.isNotEmpty()){
                        viewModel.emailOfSession(inputEmail?.text.toString())
                    }
            changeActivity()
            }else {
            Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()

            }

        })


    }


    private fun changeActivity(){
        findNavController().navigate(R.id.action_SecondFragment_to_principalFragment)

    }

}
