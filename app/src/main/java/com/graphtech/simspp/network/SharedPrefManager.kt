package com.graphtech.simspp.network

import android.content.Context
import android.content.Intent
import com.graphtech.simspp.activity.LoginActivity
import com.graphtech.simspp.model.User
import org.jetbrains.anko.startActivity

class SharedPrefManager private constructor(context: Context) {

    //check user has signed in
    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = ctx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences?.getString(KEY_USERNAME, null) != null
        }

    //get current data
    val user: User
        get() {
            val sharedPreferences = ctx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return User(
                sharedPreferences!!.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_ROLE, null),
                sharedPreferences.getString(KEY_RAYON, null)
            )
        }

    init {
        ctx = context
    }

    //save data to sharePref
    fun userLogin(user: User) {
        val sharedPreferences = ctx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putInt(KEY_ID, user.id)
        editor?.putString(KEY_USERNAME, user.username)
        editor?.putString(KEY_EMAIL, user.email)
        editor?.putString(KEY_ROLE, user.role)
        editor?.putString(KEY_RAYON, user.rayon)
        editor?.apply()
    }

    //logout
    fun logout() {
        val sharedPreferences = ctx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.clear()
        editor?.apply()
        ctx?.startActivity<LoginActivity>()
    }


    companion object{
        private val SHARED_PREF_NAME = "volleylogin"
        private val KEY_ID = "keyid"
        private val KEY_USERNAME = "keyusername"
        private val KEY_EMAIL = "keyemail"
        private val KEY_ROLE = "keyrole"
        private val KEY_RAYON = "keyrayon"

        private var mInstance: SharedPrefManager? = null
        private var ctx: Context? = null

        @Synchronized
        fun getInstance(context: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(context)
            }
            return mInstance as SharedPrefManager
        }
    }
}
