package com.example.mvvm_login_modelo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_login_modelo.Modelo.Person
import com.example.mvvm_login_modelo.Modelo.Promo
import kotlinx.android.synthetic.main.adaptador_productos.view.*
import kotlinx.android.synthetic.main.adaptador_promos.view.*

class AdaptadorPromos():RecyclerView.Adapter<AdaptadorPromos.productsViewHolder>() {

    private var ListaPromos= emptyList<Promo>()


    fun UpdateAdapter(list:List<Promo>){
        ListaPromos=list;
        notifyDataSetChanged()

        }



    inner class productsViewHolder(itemVista: View):RecyclerView.ViewHolder(itemVista){


        val image=itemVista.imagePromo
        //val title=itemVista.productos_titulo
        //val description=itemVista.productos_subtitulo



        val click= itemVista.setOnClickListener{

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productsViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.adaptador_promos,parent,false)
     return productsViewHolder(view)
    }

    override fun onBindViewHolder(holder: productsViewHolder, position: Int) {
        val producto=ListaPromos[position]

                    holder.image.setImageResource(producto.image)
    }

    override fun getItemCount()=ListaPromos.size




}