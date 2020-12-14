package com.example.mvvm_login_modelo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_login_modelo.Modelo.Person
import com.example.mvvm_login_modelo.Modelo.Producto
import kotlinx.android.synthetic.main.adaptador_productos.view.*

class AdaptadorProductos():RecyclerView.Adapter<AdaptadorProductos.productsViewHolder>() {

    private var ListaProductos= emptyList<Producto>()


    fun UpdateAdapter(list:List<Producto>){
        ListaProductos=list;
        notifyDataSetChanged()

        }



    inner class productsViewHolder(itemVista: View):RecyclerView.ViewHolder(itemVista){

        val title=itemVista.productos_titulo
        val description=itemVista.productos_subtitulo
        val price=itemVista.producto_precio
        val image=itemVista.producto_imagen

        val click= itemVista.setOnClickListener{

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productsViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.adaptador_productos,parent,false)
     return productsViewHolder(view)
    }

    override fun onBindViewHolder(holder: productsViewHolder, position: Int) {
        val producto=ListaProductos[position]
                    holder.title.text=producto.name
                    holder.description.text = producto.description
                    holder.price.text = producto.price
                    //holder.image.setImageResource()
    }

    override fun getItemCount()=ListaProductos.size




}