package com.example.mvvm_login_modelo.BaseDatos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm_login_modelo.Modelo.Person
import com.example.mvvm_login_modelo.Modelo.PersonDao



@Database(entities = [Person::class], version=3
)

abstract class BaseDatosUser:RoomDatabase(){

     abstract fun personDao(): PersonDao

    //istancia par usar la base de datos
    companion object {

        @Volatile
        private var INSTANCE: BaseDatosUser? = null

        fun getDatabase(context: Context): BaseDatosUser {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, BaseDatosUser::class.java, "Asian_DB"
                ).build()
                INSTANCE = instance
                return instance
            }
        }


    }






}





