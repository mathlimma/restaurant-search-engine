package br.com.restaurant_search_engine.adapters.output.repositories

import br.com.restaurant_search_engine.domain.entities.Restaurant
import br.com.restaurant_search_engine.domain.ports.out.RestaurantsRepository

class RestaurantRepositoryImpl : RestaurantsRepository {

    companion object {
        const val FILENAME = "restaurants"
    }

    override fun getAllRestaurants(): List<Restaurant> {
        val restaurants = CsvFileReader().readCsvFile<Restaurant>(FILENAME)

        return restaurants
    }

}