package com.example.volkandemirproje.extention


import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.getImageFromUrl(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}
