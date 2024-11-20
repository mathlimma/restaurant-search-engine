package br.com.restaurant_search_engine.adapters.controllers.dto.input

import br.com.restaurant_search_engine.domain.entities.Cuisine
import br.com.restaurant_search_engine.domain.entities.Restaurant
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import org.hibernate.validator.constraints.Length

data class RestaurantInputDTO(

    @field:Length(min = 0, max = 100)
    val name: String? = null,

    @field:Min(1) @field:Max(5)
    val customerRating: Double? = null,

    @field:Min(1) @field:Max(10)
    val distance: Double? = null,

    @field:Min(10) @field:Max(50)
    val price: Double? = null,

    @field:Length(min = 0, max = 100)
    val cuisine: String? = null
) {
    fun toDomain() = Restaurant(
        name = this.name!!,
        customerRating = this.customerRating!!,
        distance = this.distance!!,
        price = this.price!!,
        cuisine = Cuisine(name = this.cuisine!!)
    )
}

