package br.com.restaurant_search_engine.adapters.repositories

import br.com.restaurant_search_engine.domain.entities.Restaurant
import br.com.restaurant_search_engine.domain.ports.out.RestaurantRepository
import br.com.restaurant_search_engine.helper.CsvFileReader
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class RestaurantRepositoryImpl : RestaurantRepository {

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