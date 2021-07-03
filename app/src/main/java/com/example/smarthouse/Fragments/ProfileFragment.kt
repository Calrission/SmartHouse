package com.example.smarthouse.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smarthouse.R
import com.example.smarthouse.common.Api
import com.example.smarthouse.common.Models.ModelRoom
import com.example.smarthouse.common.Models.ModelScene
import com.example.smarthouse.common.initRetrofit
import com.example.smarthouse.common.showMessage
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.scene_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}