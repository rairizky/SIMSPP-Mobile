package com.graphtech.simspp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.graphtech.simspp.R
import com.graphtech.simspp.model.User
import com.graphtech.simspp.network.ApiURL
import com.graphtech.simspp.network.SharedPrefManager
import com.graphtech.simspp.network.VolleySingleton
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONObject.NULL

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //check current user
        if (SharedPrefManager.getInstance(this).isLoggedIn) {
            finish()
            startActivity<MainActivity>()
        }


        btnLogin.setOnClickListener {
            userLogin()
        }
    }

    fun userLogin() {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        if (email.isEmpty()) {
            etEmail.error = "Email tidak boleh kosong."
        }
        if (password.isEmpty()) {
            etPassword.error = "Password tidak boleh kosong."
        }

        val stringRequest = object : StringRequest(Request.Method.POST, ApiURL.getLogin(email, password),
            Response.Listener { response ->  
                try {
                    val obj = JSONObject(response)
                    val success = obj.getString("success")
                    val userJson = obj.getJSONArray("user")

                    if (success.equals("1")) {
                        for (i in 0 until userJson.length()){
                            val data = userJson.getJSONObject(i)

                            val user = User(
                                data.getInt("id"),
                                data.getString("username"),
                                data.getString("email"),
                                data.getString("role"),
                                data.getString("rayon")
                            )

                            //save to preference
                            SharedPrefManager.getInstance(applicationContext).userLogin(user)

                            //startActivity
                            finish()
                            startActivity<MainActivity>()
                            overridePendingTransition(R.anim.anim_slide_out_left, R.anim.anim_slide_out_left)
                        }
                    } else {
                        toast("Password tidak sesuai.")
                    }

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error -> toast(error.message.toString()) }) {
            @Throws
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["email"] = email
                params["password"] = password
                return params
            }
        }
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest)
    }
}
