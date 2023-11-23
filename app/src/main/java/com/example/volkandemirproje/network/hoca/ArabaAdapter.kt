package com.example.volkandemirproje.network.hoca

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.volkandemirproje.databinding.HocaItemBinding
import com.example.volkandemirproje.extention.getImageFromUrl
import com.example.volkandemirproje.model.Arabalar


class ArabaAdapter(var arabalar: ArrayList<Arabalar>, var onClik :(Int) -> Unit) : RecyclerView.Adapter<ArabaAdapter.ViewHolder>() {



    inner class ViewHolder(val binding: HocaItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HocaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return arabalar.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){

            binding.apply {
                txtIsim.text = arabalar[position].adiSoyadi
                txtMail.text=arabalar[position].eposta
                Log.e("msg1","  resim " + arabalar[position].resimURL)
                arabalar[position].resimURL?.let {
                    Log.e("msg","  resim " + it)

                  imgUser.getImageFromUrl(it)
                }

                cardView.setOnClickListener{
                    onClik(position)
                }
            }
        }

    }
    fun sortByNameAscending() {

        arabalar.sortBy { it.adiSoyadi }

        notifyDataSetChanged()
    }

    fun sortByNameDescending() {

       arabalar.sortByDescending { it.adiSoyadi }
        notifyDataSetChanged()
    }

}



