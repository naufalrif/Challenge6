package com.example.challenge6.manager

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserManager(context: Context) {
    private val dataStore : DataStore<Preferences> = context.createDataStore(name = "user_pref")

    companion object{
        val ID = preferencesKey<String>("user_id")
        val USERNAME = preferencesKey<String>("user_username")
        val ADDRESS = preferencesKey<String>("user_address")
        val BIRTHDAY = preferencesKey<String>("user_birth")
        val EMAIL = preferencesKey<String>("user_email")
        val FULLNAME = preferencesKey<String>("user_fullname")
        val PASS = preferencesKey<String>("user_pass")
    }

    suspend fun saveData(id : String, username : String, address : String, birthday : String, email : String, fullname : String, pass : String){
        dataStore.edit {
            it[ID] = id
            it[USERNAME] = username
            it[ADDRESS] = address
            it[BIRTHDAY] = birthday
            it[EMAIL] = email
            it[FULLNAME] = fullname
            it[PASS] = pass
        }
    }

    val userID : Flow<String> = dataStore.data.map {
        it[ID] ?: ""
    }

    val userUsername : Flow<String> = dataStore.data.map {
        it[USERNAME] ?: ""
    }

    val userAddress : Flow<String> = dataStore.data.map {
        it[ADDRESS] ?: ""
    }

    val userBirthday : Flow<String> = dataStore.data.map {
        it[BIRTHDAY] ?: ""
    }

    val userEmail : Flow<String> = dataStore.data.map {
        it[EMAIL] ?: ""
    }

    val userFullName : Flow<String> = dataStore.data.map {
        it[FULLNAME] ?: ""
    }

    val userPass : Flow<String> = dataStore.data.map {
        it[PASS] ?: ""
    }

    suspend fun clearData(){
        dataStore.edit {
            it.clear()
        }
    }
}