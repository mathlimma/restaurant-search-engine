package br.com.restaurant_search_engine.adapters.repositories.dao

import br.com.restaurant_search_engine.domain.entities.Cuisine
import br.com.restaurant_search_engine.domain.entities.Restaurant
import com.fasterxml.jackson.annotation.JsonProperty

data class RestaurantDAO(
    val name: String,

    @JsonProperty("customer_rating")
    val customerRating: Double,

    val distance: Double,

    val price: Double,

    @JsonProperty("cuisine_id")
    val cuisineId: Int
) {
    fun toDomain() = Restaurant(
        name = this.name,
        customerRating = this.customerRating,
        distance = this.distance,
        price = this.price,
        cuisine = Cuisine(id = this.cuisineId)
    )
}

