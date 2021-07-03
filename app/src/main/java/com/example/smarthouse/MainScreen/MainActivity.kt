package com.example.smarthouse.MainScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.smarthouse.Fragments.MainFragment
import com.example.smarthouse.Fragments.ProfileFragment
import com.example.smarthouse.Fragments.SceneFragment
import com.example.smarthouse.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var now_fragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        chooseFragment(MainFragment())

        bottom_nav_view.itemIconTintList = null

        bottom_nav_view.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> chooseFragment(MainFragment())
                R.id.scene -> chooseFragment(SceneFragment())
                R.id.profile -> chooseFragment(ProfileFragment())
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun chooseFragment(fragment: Fragment){
        if (now_fragment == null || fragment != now_fragment) {
            now_fragment = fragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, fragment)
                .commit()
        }
    }
}