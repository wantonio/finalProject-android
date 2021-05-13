package com.example.finalProject.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class CustomDividerItemDecoration(context: Context, orientation: Int): DividerItemDecoration(context, orientation) {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        // super.getItemOffsets(outRect, view, parent, state)
    }


    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val d = drawable
        val height = 150
        val width = 150
        val top = (parent.height / 2) - (height / 2)
        val bottom = top + height
        val childCount = parent.childCount

        if(childCount == 0) return

        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i);
            val params = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right
            val right = left + width

            d?.setBounds(left, top, right, bottom)

            params.setMargins(0, 0, width, 0 )
            child.layoutParams = params
            d?.draw(c);
        }
    }
}