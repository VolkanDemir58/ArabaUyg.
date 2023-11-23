package com.example.volkandemirproje.ui.hocalar


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.volkandemirproje.IkinciEkran
import com.example.volkandemirproje.databinding.ActivityMain2Binding
import com.example.volkandemirproje.model.Arabalar
import com.example.volkandemirproje.network.hoca.ArabaService
import com.example.volkandemirproje.network.hoca.ArabaAdapter
import com.example.volkandemirproje.util.Constans
import com.example.volkandemirproje.util.ObjectUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


enum class ListeTipi {
    Lıst, GRID, STAGGERED

}

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    var arabaAdapter: ArabaAdapter? = null
    val hocaService = ArabaService.build()
    var hocaList: ArrayList<Arabalar>? = null
    var currentLayout = ListeTipi.Lıst


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        var currentLayout = ListeTipi.Lıst
        binding.btnSRala.setOnClickListener {
            when (currentLayout) {
                ListeTipi.Lıst -> {
                    initRecycleView(hocaList, ListeTipi.GRID)
                    currentLayout = ListeTipi.GRID
                }
                ListeTipi.GRID -> {
                    initRecycleView(hocaList!!, ListeTipi.STAGGERED)

                    currentLayout = ListeTipi.STAGGERED
                }
                ListeTipi.STAGGERED -> {

                    initRecycleView(hocaList!!, ListeTipi.Lıst)

                    currentLayout = ListeTipi.Lıst
                }
            }
        }


        binding.txt.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity2)
            builder.setTitle("Seçiniz")
            val options = arrayOf("isme göre artan", "isme göre azalan")
            builder.setItems(options) { dialog, which ->

                when (which) {
                    0 -> {
                        arabaAdapter?.sortByNameAscending()
                    }
                    1 -> {

                        arabaAdapter?.sortByNameDescending()
                    }
                }
            }
            builder.setNegativeButton("İptal") { dialog, which ->

                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
    }


    fun init() {
        getHocalar()
        initViewModel()

    }

    fun getHocalar() {
        GlobalScope.launch {
            val hocalarList = hocaService.getHocalar()

            runOnUiThread {
                initRecycleView(hocalarList as ArrayList<Arabalar>, ListeTipi.Lıst)
            }
        }
    }

    fun initRecycleView(arabaListesi: ArrayList<Arabalar>?, tip: ListeTipi) {
        binding.apply {
            arabaAdapter = arabaListesi?.let {
                ArabaAdapter(it, { position ->
                    Toast.makeText(
                        applicationContext,
                        arabaListesi.get(position).adiSoyadi,
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@MainActivity2, IkinciEkran::class.java)
                    val hoca = arabaListesi.get(position)
                    val hocaStr = ObjectUtils.toJson(hoca)
                    intent.putExtra(Constans.HOCA_MOVE_KEY,hocaStr)

                    startActivity(intent)
                })
            }
            rcvHocalar.adapter = arabaAdapter
            if (tip == ListeTipi.Lıst) {
                rcvHocalar.layoutManager =
                    LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            } else if (tip == ListeTipi.GRID) {
                rcvHocalar.layoutManager = GridLayoutManager(applicationContext, 2)

            } else {
                rcvHocalar.layoutManager =
                    StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            }

        }

    }
    var UserViewModel: UserViewModel? = null
    var varsayilanTipim = ListeTipi.Lıst
    fun initViewModel() {

        UserViewModel = UserViewModel()

        UserViewModel?.apply {

            allUsersLiveData.observe(this@MainActivity2, Observer {
                Toast.makeText(applicationContext, "data" + it.get(0), Toast.LENGTH_LONG).show()
            })
            error.observe(this@MainActivity2, Observer {
                Toast.makeText(applicationContext, "hata" + it.localizedMessage, Toast.LENGTH_LONG)
                    .show()

            })
            loading.observe(this@MainActivity2, Observer {

            })
            /*allUserLiveData.observe(this@MainActivity2, Observer {
                Toast.makeText(applicationContext,"data"+it,Toast.LENGTH_LONG).show()
            })*/

        }

    }

}
