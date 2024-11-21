package br.com.restaurant_search_engine.adapters.controllers.dto.output

import br.com.restaurant_search_engine.domain.entities.Restaurant

data class RestaurantOutputDTO(
    val name: String,
    val customerRating: Double,
    val distance: Double,
    val price: Double,
    val cuisine: String
)

fun Restaurant.toOutputDTO() = RestaurantOutputDTO(
    name = this.name,
    customerRating = this.customerRating,
    distance = this.distance,
    price = this.price,
    cuisine = this.cuisine.name!!
)
