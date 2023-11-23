package com.example.volkandemirproje

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import com.example.volkandemirproje.databinding.ActivityMainBinding
import com.example.volkandemirproje.util.NetworkUtil
import com.example.volkandemirproje.ui.hocalar.MainActivity2

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)

       init()
    }


    fun init(){
        initCounterDown()
        initBinding()


    }

    fun initBinding(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


    fun initCounterDown() {
        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.e("Timer","Milisaniye"+millisUntilFinished)
            }
            override fun onFinish() {
                ekranGecis()
            }
        }.start()
    }
    fun ekranGecis(){
        if (NetworkUtil.isInternetAvaible(applicationContext)){
            Toast.makeText(applicationContext,"İntermet var ",Toast.LENGTH_LONG).show()
            startActivity(Intent(this@MainActivity, MainActivity2::class.java))
            finish()
        }else{
            Toast.makeText(applicationContext,"İnternet yok",Toast.LENGTH_LONG).show()
            startActivity(
                Intent(
                    Settings.ACTION_WIFI_SETTINGS)
            )
        }


    }}
