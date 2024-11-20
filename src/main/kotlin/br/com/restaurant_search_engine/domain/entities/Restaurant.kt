package br.com.restaurant_search_engine.domain.entities

data class Restaurant (
    private val name: String,
    private val customerRating: Double,
    private val distance: Double,
    private val price: Double,
    private val cuisineId: Int
)

