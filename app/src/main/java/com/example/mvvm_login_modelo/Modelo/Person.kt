package com.example.mvvm_login_modelo.Modelo

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Person(@PrimaryKey
 val email:String,
 val password:String,
 val name:String,
 val phone:String


)