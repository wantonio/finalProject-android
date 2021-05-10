package com.example.finalProject.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.finalProject.R
import com.example.finalProject.databinding.EmptyListViewBinding

class EmptyListView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: EmptyListViewBinding =
        EmptyListViewBinding.inflate(LayoutInflater.from(context), this)

    init {

        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.EmptyListView,
            defStyleAttr,
            defStyleRes
        )

        binding.textEmpty.text = typedArray.getString(R.styleable.EmptyListView_textLabel)

        typedArray.recycle()
    }
}