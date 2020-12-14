package com.example.mvvm_login_modelo

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
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.registro_fragment.*


class RegistroFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase


    private val viewModel: AppViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()
        dbReference=database.reference.child("User")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.registro_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)




        val boxName=registro_nombre
        val boxEmail=registro_correo
        val boxPhone=registro_telefono
        val boxPassword=registro_contrasena
        val buttonRegistrar=registro_boton


            buttonRegistrar.setOnClickListener {

                    if (boxName.text.isEmpty()||boxEmail.text.isEmpty()||boxPhone.text.isEmpty()
                        ||boxPassword.text.isEmpty() ){
                        Toast.makeText(context, "RELLENO TODOS LOS CAMPOS ", Toast.LENGTH_LONG).show()
                    } else {
                        val name = boxName!!.text.toString()
                        val email = boxEmail!!.text.toString()
                        val phone = boxPhone!!.text.toString()
                        val password = boxPassword!!.text.toString()

                        auth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener{task ->
                                if (task.isSuccessful) {



                                    Log.d("TAG", "createUserWithEmail:success")
                                    Toast.makeText(context, "REGISTRO  EXITOSO.", Toast.LENGTH_SHORT).show()
                                    val user = auth.currentUser
                                    val tokenEmail = user?.email

                                    sendEmail(user)
                                     //Add More Data
                                   /*val userBD= user?.uid?.let { it1 -> dbReference.child(it1) }
                                    userBD?.child("Name")?.setValue(name)
                                    userBD?.child("Email")?.setValue(email)
                                    userBD?.child("Phone")?.setValue(phone)
                                    */

                                    findNavController().navigate(R.id.action_registroFragment_to_SecondFragment)

                                } else {

                                    Log.w("DDDDDD", "createUserWithEmail:failure", task.exception)
                                    Toast.makeText(context, "ERROR AL REGISTRARSE", Toast.LENGTH_SHORT).show()
                                    //updateUI(null)
                                }


                            }






                    }




            }




        viewModel.resultado.observe(viewLifecycleOwner, Observer {

            topText.text = it

        })







    }



    private fun sendEmail(user: FirebaseUser?){
        user?.sendEmailVerification()?.addOnCompleteListener { task->

            if (task.isComplete){
                Toast.makeText(context, "Correo Enviado", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "Error al enviar el correo", Toast.LENGTH_SHORT).show()


            }


        }

    }







}