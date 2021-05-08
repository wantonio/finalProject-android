package com.example.finalProject.models

data class Evolves(val species: Species, val evolves_to: List<Evolves>)

data class Evolution(val chain: Evolves)
