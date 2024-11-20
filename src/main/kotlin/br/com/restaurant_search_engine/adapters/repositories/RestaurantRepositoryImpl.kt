package br.com.restaurant_search_engine.adapters.repositories

import br.com.restaurant_search_engine.domain.entities.Restaurant
import br.com.restaurant_search_engine.domain.ports.out.RestaurantsRepository
import org.slf4j.LoggerFactory

class RestaurantRepositoryImpl : RestaurantsRepository {

    private val logger = LoggerFactory.getLogger(javaClass)

    companion object {
        const val FILENAME = "restaurants"
    }

    override fun getAllRestaurants(): List<Restaurant> {
        logger.info("getting all restaurants")
        val restaurants = CsvFileReader().readCsvFile<Restaurant>(FILENAME)

        return restaurants
    }

}