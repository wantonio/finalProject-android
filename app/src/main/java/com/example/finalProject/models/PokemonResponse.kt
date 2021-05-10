package com.example.finalProject.models

data class PokemonResponse(val count: Int, val next: String?, val previous: String?, val results: List<PokemonDetail>)
