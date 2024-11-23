package br.com.restaurant_search_engine.adapters.controllers.dto.input

import br.com.restaurant_search_engine.domain.entities.Cuisine
import br.com.restaurant_search_engine.domain.entities.Restaurant
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import org.hibernate.validator.constraints.Length

data class RestaurantInputDTO(

    // TODO add only non null attributes to payload

    @field:Length(min = 0, max = 100)
    val name: String? = "",

    @JsonProperty("customer_rating")
    @field:Min(1) @field:Max(5)
    val customerRating: Double? = 5.0,

    @field:Min(1) @field:Max(10)
    val distance: Double? = 10.0,

    @field:Min(10) @field:Max(50)
    val price: Double? = 50.0,

    @field:Length(min = 0, max = 100)
    val cuisine: String? = ""

) {
    fun toDomain() = Restaurant(
        name = this.name!!,
        customerRating = this.customerRating!!,
        distance = this.distance!!,
        price = this.price!!,
        cuisine = Cuisine(name = this.cuisine!!)
    )
}

