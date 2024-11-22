package br.com.restaurant_search_engine.domain.ports.`in`

import br.com.restaurant_search_engine.domain.entities.Restaurant

interface RestaurantSearchUsecase {

    fun search(restaurantInput: Restaurant): List<Restaurant>
}