package com.example.finalProject.models

data class FlavorTextLanguage (val name: String)
data class FlavorTextEntries(val flavor_text: String, val language: FlavorTextLanguage)
data class EvolutionChain(val url: String)

data class PokemonSpecies(val flavor_text_entries: List<FlavorTextEntries>, val evolution_chain: EvolutionChain)