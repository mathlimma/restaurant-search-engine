package br.com.restaurant_search_engine.domain.entities

data class Restaurant (
    val name: String,
    val customerRating: Double,
    val distance: Double,
    val price: Double,
    val cuisine: Cuisine
)

