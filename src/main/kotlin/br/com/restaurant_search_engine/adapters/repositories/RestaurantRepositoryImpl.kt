package br.com.restaurant_search_engine.adapters.repositories

import br.com.restaurant_search_engine.adapters.repositories.dao.RestaurantDAO
import br.com.restaurant_search_engine.domain.entities.Restaurant
import br.com.restaurant_search_engine.domain.ports.out.RestaurantRepository
import br.com.restaurant_search_engine.helper.CsvFileReader
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class RestaurantRepositoryImpl(private val csvFileReader: CsvFileReader) : RestaurantRepository {

    private val logger = LoggerFactory.getLogger(javaClass)
    private var restaurants: List<RestaurantDAO>? = null

    companion object {
        const val FILENAME = "restaurants"
    }

    override fun getAllRestaurants(): List<Restaurant> {
        logger.info("getting all restaurants")
        when (this.restaurants) {
            null -> {
                val restaurantList = csvFileReader.readCsvFile(FILENAME, RestaurantDAO::class.java)
                this.restaurants = restaurantList
            }
            else -> logger.debug("csv file wont be loaded - cache hit: {}", this.restaurants)
        }
        return restaurants!!.map { it.toDomain() }
    }

}