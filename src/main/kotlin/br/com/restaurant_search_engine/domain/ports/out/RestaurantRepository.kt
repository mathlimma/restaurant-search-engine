package br.com.restaurant_search_engine.domain.ports.out

import br.com.restaurant_search_engine.domain.entities.Restaurant

interface RestaurantRepository {

    fun getAllRestaurants() : List<Restaurant>
}