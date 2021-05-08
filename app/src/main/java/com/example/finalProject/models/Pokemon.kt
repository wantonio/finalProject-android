package com.example.finalProject.models

data class SpritesOtherDream(val front_default: String)

data class SpritesOther(val dream_world: SpritesOtherDream)

data class Sprites(val front_default: String, val other: SpritesOther)

data class StatObj(val name: String)

data class Stat(val base_stat: Int, val stat: StatObj)

data class TypeObj(val name: String)

data class Type(val type: TypeObj)

data class Pokemon(val id: Int, val name: String, val species: Species, val sprites: Sprites, val stats: List<Stat>, val height: Int, val weight: Int, val types: List<Type>)