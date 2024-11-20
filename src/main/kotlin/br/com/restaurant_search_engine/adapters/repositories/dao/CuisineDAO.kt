package br.com.restaurant_search_engine.adapters.repositories.dao

import br.com.restaurant_search_engine.domain.entities.Cuisine

data class CuisineDAO(
    val id: Int,
    val name: String
) {
    fun toDomain() = Cuisine(
        id = this.id,
        name = this.name
    )
}