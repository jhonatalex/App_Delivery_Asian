package com.example.mvvm_login_modelo.Modelo

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvm_login_modelo.Modelo.Person


@Dao
interface PersonDao {


   @Query ("SELECT * FROM Person")
    fun restoreSessionUser():LiveData<Person>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(people:Person)

    @Query("DELETE FROM Person ")
    suspend fun deleteAllUser()


}