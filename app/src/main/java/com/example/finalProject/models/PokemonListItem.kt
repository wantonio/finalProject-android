package com.example.finalProject.models

data class PokemonListItem(val name: String, val url: String, var isFavorite: Boolean = false, var isRecent: Boolean = false)