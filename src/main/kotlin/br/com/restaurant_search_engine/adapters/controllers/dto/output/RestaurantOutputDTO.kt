package br.com.restaurant_search_engine.adapters.controllers.dto.output

import br.com.restaurant_search_engine.domain.entities.Restaurant

data class RestaurantOutputDTO(
    private val name: String,
    private val customerRating: Double,
    private val distance: Double,
    private val price: Double,
    private val cuisine: String
)

fun Restaurant.toOutputDTO() = RestaurantOutputDTO(
    name = this.name,
    customerRating = this.customerRating,
    distance = this.distance,
    price = this.price,
    cuisine = this.cuisine.name!!
)
