package com.example.smarthouse.SplashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.smarthouse.AuthScreen.AuthActivity
import com.example.smarthouse.MainScreen.MainActivity
import com.example.smarthouse.R
import com.example.smarthouse.RegScreen.RegActivity
import com.example.smarthouse.common.Info
import com.example.smarthouse.common.Models.ModelUser

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
           var _intent = Intent(this, AuthActivity::class.java)
            if (getSharedPreferences("0", 0).getBoolean("isFirst", true)){
                _intent = Intent(this, RegActivity::class.java)
            }
//            _intent = Intent(this, MainActivity::class.java)
//            Info.modelUser = ModelUser("test", "test", "test", "test@gmail.com")
            startActivity(_intent)
            finish()
        }, 1000)
       }
}