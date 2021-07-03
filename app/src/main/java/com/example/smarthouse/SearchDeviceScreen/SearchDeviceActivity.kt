package com.example.smarthouse.SearchDeviceScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthouse.R
import com.example.smarthouse.common.ModelTag
import com.example.smarthouse.common.Models.ModelDevice
import kotlinx.android.synthetic.main.activity_search_device.*
import kotlinx.android.synthetic.main.item_rec_tag.*

class SearchDeviceActivity : AppCompatActivity() {
    var nowTag: TextView? = null
    var scrollWithTag: Boolean = false
    val list_tag = arrayListOf<Int>(
        R.id.new_product,
        R.id.camera,
        R.id.on_off,
        R.id.light,
        R.id.b_tech,
        R.id.c_tech,
        R.id.clear_air,
        R.id.sensor,
        R.id.hp
    )

    lateinit var linearLayoutRec: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_device)
        linearLayoutRec = LinearLayoutManager(this)

        back_search.setOnClickListener {
            finish()
        }

        rec_search.apply {
            adapter = AdapterTag(
                arrayListOf(
                    ModelTag("New Product", arrayListOf(
                        ModelDevice("", "cleaner", "Робот пылесос с функцией очистки"),
                        ModelDevice("", "cleaner air", "ROIDMI EVE"),
                        ModelDevice("", "sensor", "Устройство контроля температуры"),
                        ModelDevice("", "cleaner air", "Очиститель воздуха Mi Pro H"),
                        ModelDevice("", "cleaner air", "Очиститель воздуха Mi 3H"),
                        ModelDevice("", "cleaner air", "Очиститель воздуха Mi 3C"),
                        ModelDevice("", "camera", "IMILAB домашняя камера"),
                        ModelDevice("", "sensor", "Датчик движения и освещенния"),
                        ModelDevice("", "other", "Прочее"),
                    )),
                    ModelTag("Камера", arrayListOf(
                        ModelDevice("", "camera", "Камера наблюдения MI 360 1080P"),
                        ModelDevice("", "camera", "Камера наблюдения MI Basic 1080P"),
                        ModelDevice("", "camera", "IP-камера домашнего наблюдения"),
                        ModelDevice("", "camera", "IMILAB домашняя камера безопастности"),
                        ModelDevice("", "camera", "IP-камера домашнего видеонаблюдения"),
                        ModelDevice("", "camera", "IP-камера домашнего видеонаблюдения"),
                    )),
                    ModelTag("Пылесосы", arrayListOf(
                        ModelDevice("", "cleaner", "Робот пылесос с функцией очистки"),
                        ModelDevice("", "cleaner", "VIOMI V3 Max"),
                        ModelDevice("", "cleaner", "Viomi V3"),
                        ModelDevice("", "cleaner", "Viomi SE"),
                        ModelDevice("", "cleaner", "Viomi S9"),
                        ModelDevice("", "cleaner", "Mi Robot Vacuum"),
                    )),
                    ModelTag("Освещение", arrayListOf(
                        ModelDevice("", "light", "Умная лампа Mi LED Smart Build"),
                        ModelDevice("", "light", "Светодиодная лампа Aqara"),
                        ModelDevice("", "light", "Светодиодная лента Yeelight"),
                    )),
                    ModelTag("Бытовая техника", arrayListOf(
                        ModelDevice("", "cleaner air", "Очиститель воздуха Mi Pro"),
                        ModelDevice("", "sensor", "Устройство контроля температуры"),
                        ModelDevice("", "cleaner air", "Очиститель воздуха Mi Pro H"),
                        ModelDevice("", "light", "Светодиодная лампа Aqara"),
                        ModelDevice("", "cleaner", "Робот пылесос с функцией очистки"),
                        ModelDevice("", "sensor", "Датчик движения и освещенния")
                    )),
                    ModelTag("Кухонная электроника", arrayListOf(
                        ModelDevice("", "cleaner air", "Очиститель воздуха Mi Pro"),
                        ModelDevice("", "sensor", "Устройство контроля температуры"),
                        ModelDevice("", "cleaner air", "Очиститель воздуха Mi Pro H"),
                        ModelDevice("", "light", "Светодиодная лампа Aqara"),
                        ModelDevice("", "sensor", "Датчик движения и освещенния")
                    )),
                    ModelTag("Очитска воздуха", arrayListOf(
                        ModelDevice("", "cleaner air", "Очиститель воздуха Mi Pro"),
                        ModelDevice("", "cleaner air", "Очиститель воздуха Mi Pro H"),
                        ModelDevice("", "cleaner air", "Очиститель воздуха Mi 3H"),
                        ModelDevice("", "cleaner air", "Очиститель воздуха Mi 3C"),
                        ModelDevice("", "cleaner air", "ROIDMI EVE"),
                    )),
                    ModelTag("Датчик", arrayListOf(
                        ModelDevice("", "sensor", "Датчик движения и освещенния"),
                        ModelDevice("", "sensor", "Устройство контроля температуры"),
                    )),
                    ModelTag("Здоровье", arrayListOf(
                        ModelDevice("", "cleaner air", "ROIDMI EVE"),
                        ModelDevice("", "scooter", "Mi Essential Pro 2"),
                        ModelDevice("", "sensor", "Устройство контроля температуры"),
                        ModelDevice("", "cleaner", "Робот пылесос с функцией очистки"),
                        ModelDevice("", "cleaner air", "Очиститель воздуха Mi Pro H"),
                        ModelDevice("", "cleaner air", "Очиститель воздуха Mi 3H"),
                        ModelDevice("", "cleaner air", "Очиститель воздуха Mi 3C"),
                    ))
                )
            )
            layoutManager = linearLayoutRec
        }

        for (i in list_tag){
            findViewById<TextView>(i).setOnClickListener {
                switchNowTag(it as TextView)
                scrollWithTag = true
                linearLayoutRec.smoothScrollToPosition(rec_search, RecyclerView.State(), list_tag.indexOf(i))
                Handler().postDelayed({scrollWithTag = false}, 650)
                //linearLayoutRec.scrollToPosition(list_tag.indexOf(i))
            }
        }
        switchNowTag(findViewById(R.id.new_product))
        rec_search.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!scrollWithTag) {
//                    val firstPos = linearLayoutRec.findFirstVisibleItemPosition()
//                    val lastPos = linearLayoutRec.findLastVisibleItemPosition()
                    //switchNowTag(findViewById(list_tag[firstPos - 2 + (lastPos + 1 / firstPos + 1).toInt()]))
                    switchNowTag(findViewById(list_tag[linearLayoutRec.findFirstVisibleItemPosition()]))
                }
            }
        })
    }

    private fun switchNowTag(view: TextView){
        if (nowTag != null){
            nowTag!!.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBack))
            nowTag!!.setTextColor(ContextCompat.getColor(this, R.color.colorText))
        }
        view.setBackgroundResource(R.drawable.shape_search_device)
        view.setTextColor(ContextCompat.getColor(this, R.color.colorBack))
        nowTag = view
    }
}