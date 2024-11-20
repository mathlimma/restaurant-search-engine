package br.com.restaurant_search_engine.adapters.dto.output

data class RestaurantOutputDTO (
    private val name: String,
    private val customerRating: Double,
    private val distance: Double,
    private val price: Double,
    private val cuisineId: Int
)

