package com.example.volkandemirproje

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.volkandemirproje.databinding.ActivityIkinciEkranBinding
import com.example.volkandemirproje.extention.getImageFromUrl
import com.example.volkandemirproje.model.Arabalar
import com.example.volkandemirproje.util.Constans
import com.example.volkandemirproje.util.ObjectUtils

class IkinciEkran : AppCompatActivity() {

    private lateinit var binding: ActivityIkinciEkranBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIkinciEkranBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.getStringExtra(Constans.HOCA_MOVE_KEY)?.let {
            val hoca = ObjectUtils.fromJson(it, Arabalar::class.java)
            Toast.makeText(applicationContext, hoca.adiSoyadi, Toast.LENGTH_LONG).show()
            hoca.resimURL?.let { url ->
                binding.imgDetay.getImageFromUrl(url)
            }
            binding.txtName.text = hoca.adiSoyadi
            binding.txtMail2.text = hoca.eposta
            binding.txtDers.text=hoca.description

        }
    }
}
