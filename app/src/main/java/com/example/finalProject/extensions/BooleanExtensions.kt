package com.example.finalProject.extensions

import android.view.View

fun Boolean.mapToVisibility() : Int = if (this) View.VISIBLE else View.GONE