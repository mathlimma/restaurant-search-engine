package br.com.restaurant_search_engine.adapters.output.repositories.dto

data class RestaurantDTO (
    private val name: String,
    private val customerRating: Double,
    private val distance: Double,
    private val price: Double,
    private val cuisineId: Int
)

