package br.com.restaurant_search_engine.adapters.dto.input

data class RestaurantInputDTO (
    private val name: String,
    private val customerRating: Double,
    private val distance: Double,
    private val price: Double,
    private val cuisine: String
)

