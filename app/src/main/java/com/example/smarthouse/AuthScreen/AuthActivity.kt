package com.example.smarthouse.AuthScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.smarthouse.MainScreen.MainActivity
import com.example.smarthouse.R
import com.example.smarthouse.RegScreen.RegActivity
import com.example.smarthouse.common.Models.ModelUser
import com.example.smarthouse.common.OnResult
import com.example.smarthouse.common.auth
import com.example.smarthouse.common.checkEmail
import com.example.smarthouse.common.showMessage
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val focus_edittext = View.OnFocusChangeListener{ view, bool ->
            if (bool) {
                view.setBackgroundResource(R.drawable.activ_edittext_shape)
                (view as EditText).setHintTextColor(resources.getColor(R.color.colorNumberHistory))
            }else {
                view.setBackgroundResource(R.drawable.edittext_shape)
                (view as EditText).setHintTextColor(resources.getColor(R.color.colorGradient1))
            }
        }

        email_auth.onFocusChangeListener = focus_edittext
        password_auth.onFocusChangeListener = focus_edittext
        email_auth.requestFocus()

        to_reg.setOnClickListener {
            startActivity(Intent(this, RegActivity::class.java))
            finish()
        }

        auth.setOnClickListener {
                val email = email_auth.text.toString()
                val password = password_auth.text.toString()
                    if (checkEmail(email))
                        if (email.isNotEmpty() && password.isNotEmpty())
                            auth(this, ModelUser("", "", password, email), object: OnResult {
                                override fun onResult() {
                                    startActivity(Intent(applicationContext, MainActivity::class.java))
                                    finish()
                                }
                            })
                        else
                            showMessage(this, "Заполните все поля")
                    else
                        showMessage(this, "Почта не по шаблону")
        }
    }
}