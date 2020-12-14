package com.example.mvvm_login_modelo


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import androidx.recyclerview.widget.LinearLayoutManager

import com.example.mvvm_login_modelo.Modelo.Producto
import com.example.mvvm_login_modelo.Modelo.Promo
import com.example.mvvm_login_modelo.ViewModel.AppViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_principal.*
import kotlinx.android.synthetic.main.fragment_second.*


class PrincipalFragment : Fragment() {


    lateinit var myAdapter: AdaptadorProductos
    lateinit var mAdapter: AdaptadorPromos

    private var ListaProductos=emptyList<Producto>()
    private var ListaPromos=emptyList<Promo>()


    private val viewModel: AppViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        myAdapter=AdaptadorProductos()
        mAdapter=AdaptadorPromos()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_principal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            viewModel.deleteUser()
            findNavController().navigate(R.id.action_principalFragment_to_FirstFragment)
            viewModel.updateUI(null)
        }

        viewModel.election.observe(viewLifecycleOwner, Observer {
            Textwelcome.text = it
            if (it.isNotEmpty()){
                viewModel.buttonRegisterClicked(it)
            }

        })

        if (Textwelcome.text.isNotEmpty()){
            viewModel.buttonRegisterClicked(Textwelcome.text.toString())
        }


        // RECYCLER 1
        conten_recycler.layoutManager=LinearLayoutManager(context)
        conten_recycler.adapter=myAdapter
        myAdapter.UpdateAdapter(buscarDatos())


        //viewModel.publicoMostrarLista.observe(viewLifecycleOwner, Observer {
          //  myAdapter.UpdateAdapter(it)
        //})


        // RECYCLER 2
        val horizontalLayoutManagaer = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        conten_promos.layoutManager=horizontalLayoutManagaer
        conten_promos.adapter=mAdapter
        mAdapter.UpdateAdapter(getPromo())



    }


    fun getPromo():List<Promo>{

        ListaPromos= listOf(
            Promo(R.drawable.camaron, "CAMAROM", "01/10/2000"),
            Promo(R.drawable.promo, "CAMAROM", "01/10/2000"),
            Promo(R.drawable.gohanpromo, "CAMAROM", "01/10/2000"),
            Promo(R.drawable.assian, "CAMAROM", "01/10/2000")
        )

       return   ListaPromos
    }


    fun buscarDatos():List<Producto>{

       ListaProductos= listOf(
          Producto( id=1, name="Gohhan", description = "Premiun", price = "2000", ingredients =  "Palta"),
           Producto( id=2, name="Giosas", description = "Premiun", price = "3000", ingredients =  "Palta"),
           Producto( id=3, name="Cangri", description = "Premiun", price = "2000", ingredients =  "Cangrejo"),
           Producto( id=4, name="Roll", description = "Premiun", price = "2000", ingredients =  "Cibulet")
       )

       return  ListaProductos
   }





}