package com.example.smarthouse.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smarthouse.R
import com.example.smarthouse.common.Api
import com.example.smarthouse.common.Models.ModelRoom
import com.example.smarthouse.common.Models.ModelScene
import com.example.smarthouse.common.OnCLickMenu
import com.example.smarthouse.common.initRetrofit
import com.example.smarthouse.common.showMessage
import kotlinx.android.synthetic.main.menu_tags.*
import kotlinx.android.synthetic.main.scene_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SceneFragment: Fragment() {

    var menu_now: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.scene_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        text_user_scene.setOnClickListener {
            showMenuHome()
            showAlpha()
        }

        alpha.setOnClickListener {
            hideAlpha()
            hideMenu()
        }

        menu_down.setOnClickListener {
            showAlpha()
            showMenuTags()
        }

        initRetrofit().create(Api::class.java).getScene().enqueue(object: Callback<List<ModelScene>>{
            override fun onResponse(
                call: Call<List<ModelScene>>,
                response: Response<List<ModelScene>>
            ) {
                if (response.isSuccessful){
                    rec_scene.apply {
                        adapter = AdapterScene(response.body()!!)
                        layoutManager = LinearLayoutManager(requireContext())
                    }
                }else{
                    showMessage(requireContext(), "Fail get SCENE: Null body")
                }
            }

            override fun onFailure(call: Call<List<ModelScene>>, t: Throwable) {
                showMessage(requireContext(), "Fail get SCENE")
            }

        })

        initRetrofit().create(Api::class.java).getRoomList().enqueue(object: Callback<List<ModelRoom>>{
            override fun onResponse(
                call: Call<List<ModelRoom>>,
                response: Response<List<ModelRoom>>
            ) {
                if (response.isSuccessful) {
                    val list_ = response.body()!!.map { elem -> elem.name }.toMutableList()
                    list_.add(0, "Все")
                    rec_tags_menu.apply {
                        adapter = AdapterMenuTags(list_, object: OnCLickMenu{
                            override fun onClickRoom(pos: Int) {
                                menu_down.text = list_[pos]
                                hideMenu()
                            }
                        })
                        layoutManager = GridLayoutManager(requireContext(), 4)
                    }
                }else{
                    showMessage(requireContext(), "Fail get list ROOM: Null body")
                }
            }

            override fun onFailure(call: Call<List<ModelRoom>>, t: Throwable) {
                showMessage(requireContext(), "Fail get ROOM")
            }
        })
    }

    private fun showMenuHome(){
        menu_now = menu_home_scene
        menu_now!!.visibility = View.VISIBLE
        showAlpha()
    }

    private fun showMenuTags(){
        menu_now = menu_tags
        menu_now!!.visibility = View.VISIBLE
        showAlpha()
    }

    private fun hideAlpha(){
        alpha.visibility = View.GONE
    }

    private fun showAlpha(){
        alpha.visibility = View.VISIBLE
    }

    private fun hideMenu(){
        if (menu_now != null) {
            menu_now!!.visibility = View.GONE
            hideAlpha()
            menu_now = null
        }
    }
}