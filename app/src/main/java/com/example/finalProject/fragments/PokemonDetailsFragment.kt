package com.example.finalProject.fragments

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.finalProject.R
import com.example.finalProject.databinding.FragmentPokemonDetailsBinding

class PokemonDetailsFragment : Fragment() {
    // private val arguments: PokemonDetailsFragmentArgs by navArgs()
    private var _binding: FragmentPokemonDetailsBinding ? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)
        initStats()
        return binding.root
    }

    private fun initStats() {
        for(child in binding.wrapStats.children) {
            val auxTextView:TextView = (child as TextView)
            val text:String = getColoredStat( auxTextView.text.toString(), "76" )
            auxTextView.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        }
    }

    private fun getColoredStat(stat: String, value: String): String {
        val color:String = context?.let { ContextCompat.getColor(it, R.color.red) }.toString()

        return "<font color=$color>$stat:</font> $value"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}