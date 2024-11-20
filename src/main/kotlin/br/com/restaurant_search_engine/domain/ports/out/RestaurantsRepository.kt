package br.com.restaurant_search_engine.domain.ports.out

import br.com.restaurant_search_engine.domain.entities.Restaurant

interface RestaurantsRepository {

    fun getAllRestaurants() : List<Restaurant>
}