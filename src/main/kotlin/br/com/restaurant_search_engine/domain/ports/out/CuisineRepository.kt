package br.com.restaurant_search_engine.domain.ports.out

import br.com.restaurant_search_engine.domain.entities.Cuisine

interface CuisineRepository {

    fun getAllCuisines(): List<Cuisine>
    fun getCuisinesById(cuisineId: Int): Cuisine
}