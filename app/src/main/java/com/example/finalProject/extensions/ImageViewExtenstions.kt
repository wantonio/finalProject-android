package com.example.finalProject.extensions

import android.net.Uri
import android.widget.ImageView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

fun ImageView.loadSvg(url: String?) {
    GlideToVectorYou
        .init()
        .with(this.context)
        .load(Uri.parse(url), this)
}