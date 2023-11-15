package org.jash.common.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("android:image_url")
fun loadImage(image:ImageView, url:String) {
    Glide.with(image)
        .load(url)
        .into(image)
}