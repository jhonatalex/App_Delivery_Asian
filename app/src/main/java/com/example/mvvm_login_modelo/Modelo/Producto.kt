package com.example.mvvm_login_modelo.Modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data  class Producto(@PrimaryKey(autoGenerate = true)
                     val id: Int,
                     val name:String,
                     val description:String,
                     val price:String,
                     val ingredients:String
)