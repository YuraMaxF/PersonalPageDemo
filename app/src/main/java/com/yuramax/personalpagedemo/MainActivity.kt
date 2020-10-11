package com.yuramax.personalpagedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mHandler = Handler()

        initEvent()
    }

    private fun initEvent() {
        btnRandom.setOnClickListener {
            //随机产生view
            rainIv.startRain()
        }
        btnAddView.setOnClickListener {
            var view = layoutInflater.inflate(R.layout.view_middle,null)
            view?.let {
                it.findViewById<ImageView>(R.id.ivHeader).setOnClickListener {
                    Toast.makeText(this@MainActivity,"clicked!",Toast.LENGTH_SHORT).show()
                }
                rainIv.addRainView(it)
            }
        }
        btnStop.setOnClickListener {
            rainIv.stopRain()
        }
    }
}
