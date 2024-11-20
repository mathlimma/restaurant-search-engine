package br.com.restaurant_search_engine.adapters.dto.input

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import org.hibernate.validator.constraints.Length

data class RestaurantInputDTO(

    @field:Length(min = 0, max = 100)
    private val name: String? = null,

    @field:Min(1) @field:Max(5)
    private val customerRating: Double? = null,

    @field:Min(1) @field:Max(10)
    private val distance: Double? = null,

    @field:Min(10) @field:Max(50)
    private val price: Double? = null,

    @field:Length(min = 0, max = 100)
    private val cuisine: String? = null
)

