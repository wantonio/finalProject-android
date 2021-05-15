package com.example.finalProject.extensions

import android.net.Uri
import android.widget.ImageView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.squareup.picasso.Picasso

fun ImageView.loadSvg(url: String?) {
    GlideToVectorYou
        .init()
        .with(this.context)
        .load(Uri.parse(url), this)
}

fun ImageView.loadImage(url: String?, isSvg: Boolean = false) {
    if(isSvg) this.loadSvg(url)
    else this.loadPngJpg(url)
}

fun ImageView.loadPngJpg(url: String?) {
    Picasso.get().load(url).into(this)
}

fun ImageView.loadMaybeSvg(urlSvg: String?, urlOther: String) {
    val isSvg = urlSvg != null
    val imageUrl = urlSvg ?: urlOther

    this.loadImage(imageUrl, isSvg)
}