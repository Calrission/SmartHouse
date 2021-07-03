package com.example.smarthouse.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smarthouse.MessageScreen.MessageActivity
import com.example.smarthouse.R
import com.example.smarthouse.SearchDeviceScreen.SearchDeviceActivity
import com.example.smarthouse.common.Api
import com.example.smarthouse.common.Models.ModelRoom
import com.example.smarthouse.common.OnCLickMenu
import com.example.smarthouse.common.initRetrofit
import com.example.smarthouse.common.showMessage
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.menu_room.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment: Fragment() {
    var menu_now: View? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        add_device.setOnClickListener {
            startActivity(Intent(requireContext(), SearchDeviceActivity::class.java))
        }

        text_user.setOnClickListener {
            showMenuHome()
        }

        bell.setOnClickListener {
            startActivity(Intent(requireContext(), MessageActivity::class.java))
        }

        tab_menu.setOnClickListener {
            showMenuRoom()
        }

        val on_click_alpha = View.OnClickListener{
            hideMenu()
        }

        alpha_1.setOnClickListener(on_click_alpha)
        alpha_2.setOnClickListener(on_click_alpha)

        initRetrofit().create(Api::class.java).getFavorite().enqueue(object: Callback<List<String>>{
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful){
                    val listID = response.body()
                    initRetrofit().create(Api::class.java).getRoomList().enqueue(object: Callback<List<ModelRoom>>{
                        override fun onResponse(
                            call: Call<List<ModelRoom>>,
                            response: Response<List<ModelRoom>>
                        ) {
                            if (response.isSuccessful) {
                                val listData = response.body()!!.toMutableList()
                                listData.add(0, ModelRoom("Favorites", "-1", listID!!))
                                pager.adapter = AdapterViewPager(listData)

                                TabLayoutMediator(tab, pager) { tab, pos ->
                                    tab.text = listData[pos].name
                                }.attach()

                                rec_room_menu.apply {
                                    adapter = AdapterMenuRoom(listData, object: OnCLickMenu{
                                        override fun onClickRoom(pos: Int) {
                                            tab.selectTab(tab.getTabAt(pos))
                                            hideMenu()
                                        }
                                    })
                                    layoutManager = LinearLayoutManager(requireContext())
                                }
                            }else{
                                showMessage(requireContext(), "Fail get list ROOM: Null body")
                            }
                        }

                        override fun onFailure(call: Call<List<ModelRoom>>, t: Throwable) {
                            showMessage(requireContext(), "Fail get ROOM")
                        }
                    })
                }else{
                    showMessage(requireContext(), "Fail get FAVORITE: Null body")
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                showMessage(requireContext(), "Fail get FAVORITE")
            }

        })
    }

    private fun showMenuRoom(){
        menu_now = menu_list_room
        menu_now!!.visibility = View.VISIBLE
        showAlpha()
    }

    private fun hideMenu(){
        if (menu_now != null) {
            menu_now!!.visibility = View.GONE
            hideAlpha()
            menu_now = null
        }
    }

    private fun showMenuHome(){
        menu_now = menu_home
        menu_now!!.visibility = View.VISIBLE
        showAlpha()
    }

    private fun hideAlpha(){
        alpha_1.visibility = View.GONE
        alpha_2.visibility = View.GONE
    }

    private fun showAlpha(){
        alpha_1.visibility = View.VISIBLE
        alpha_2.visibility = View.VISIBLE
    }
}