package com.example.mvvm_login_modelo

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mvvm_login_modelo.ViewModel.AppViewModel
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.coroutines.launch


class FirstFragment : Fragment() {

    private val viewModel: AppViewModel by activityViewModels()

override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        session()


        view.findViewById<Button>(R.id.bt_crearCuenta).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_registroFragment)
        }

        view.findViewById<Button>(R.id.btnLogin).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }


    }

    private fun  session(){
        viewModel.restoreSession().observe(viewLifecycleOwner, Observer {
            if (it!=null){
                viewModel.emailOfSession(it.email)
                findNavController().navigate(R.id.action_FirstFragment_to_principalFragment)
            }


        })


    }




}