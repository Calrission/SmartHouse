package com.example.smarthouse.RegScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.smarthouse.AuthScreen.AuthActivity
import com.example.smarthouse.MainScreen.MainActivity
import com.example.smarthouse.R
import com.example.smarthouse.common.Models.ModelUser
import com.example.smarthouse.common.OnResult
import com.example.smarthouse.common.checkEmail
import com.example.smarthouse.common.reg
import com.example.smarthouse.common.showMessage
import kotlinx.android.synthetic.main.activity_reg.*

class RegActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        val focus_edittext = View.OnFocusChangeListener{ view, bool ->
            if (bool) {
                view.setBackgroundResource(R.drawable.activ_edittext_shape)
                (view as EditText).setHintTextColor(resources.getColor(R.color.colorNumberHistory))
            }else {
                view.setBackgroundResource(R.drawable.edittext_shape)
                (view as EditText).setHintTextColor(resources.getColor(R.color.colorGradient1))
            }
        }

        email_reg.onFocusChangeListener = focus_edittext
        firstname_reg.onFocusChangeListener = focus_edittext
        lastname_reg.onFocusChangeListener = focus_edittext
        password_reg.onFocusChangeListener = focus_edittext
        password2_reg.onFocusChangeListener = focus_edittext

        to_auth.setOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }

        reg.setOnClickListener {
            val email = email_reg.text.toString()
            val password = password_reg.text.toString()
            val password2 = password2_reg.text.toString()
            val firstname = firstname_reg.text.toString()
            val lastname = lastname_reg.text.toString()
            if (password == password2)
                if (checkEmail(email))
                    if (email.isNotEmpty() && password.isNotEmpty() && firstname.isNotEmpty() && lastname.isNotEmpty()){
                        reg(this, ModelUser(firstname, lastname, password, email), object :
                            OnResult {
                            override fun onResult() {
                                startActivity(Intent(applicationContext, MainActivity::class.java))
                                finish()
                            }
                        })
                }else
                        showMessage(this, "Заполните все поля")
                else
                    showMessage(this, "Почта не по шаблону")
            else
                showMessage(this, "Пароли не совпадают")
        }
    }
}