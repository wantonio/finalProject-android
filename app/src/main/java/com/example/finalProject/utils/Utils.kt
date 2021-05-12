package com.example.finalProject.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue


class Utils {
    companion object {
        fun getPixelValue(context: Context, dimenId: Float): Int {
            val resources: Resources = context.resources
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dimenId,
                resources.displayMetrics
            ).toInt()
        }

        fun getResId(resName: String?, c: Class<*>): Int {
            return try {
                val idField = c.getDeclaredField(resName)
                idField.getInt(idField)
            } catch (e: Exception) {
                e.printStackTrace()
                -1
            }
        }
    }
}