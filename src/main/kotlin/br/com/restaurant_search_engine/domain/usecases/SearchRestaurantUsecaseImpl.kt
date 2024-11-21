package br.com.restaurant_search_engine.domain.usecases

import br.com.restaurant_search_engine.domain.entities.Cuisine
import br.com.restaurant_search_engine.domain.entities.Restaurant
import br.com.restaurant_search_engine.domain.ports.`in`.SearchRestaurantUsecase
import br.com.restaurant_search_engine.domain.ports.out.CuisineRepository
import br.com.restaurant_search_engine.domain.ports.out.RestaurantRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SearchRestaurantUsecaseImpl(
    private val restaurantRepository: RestaurantRepository,
    private val cuisineRepository: CuisineRepository
) : SearchRestaurantUsecase {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun search(restaurantInput: Restaurant): List<Restaurant> {
        logger.info("starting search - restaurant searched params: {}", restaurantInput)

        val cuisines = this.cuisineRepository.getAllCuisines()
        val restaurants = this.restaurantRepository.getAllRestaurants()

        val restaurantFiltered =
            this.handleFilter(restaurants = restaurants, cuisines = cuisines, resInput = restaurantInput)

        return restaurantFiltered
    }

    private fun handleFilter(
        restaurants: List<Restaurant>,
        cuisines: List<Cuisine>,
        resInput: Restaurant
    ): List<Restaurant> {
        logger.debug("filtering by restaurant : {}", resInput)

        val cuisine = cuisines.find { it.name?.lowercase() == resInput.cuisine.name?.lowercase() }

        when (cuisine) {
            null -> {
                logger.warn("could not find cuisine : {}", resInput.cuisine.name)
                throw IllegalArgumentException("could not find cuisine: ${resInput.cuisine.name}")
            }
        }
        logger.debug("found cuisine : {}", cuisine)

        // revisit order
        return restaurants.filter {
            it.cuisine.id == cuisine?.id
                    && it.name.lowercase().contains(resInput.name.lowercase())
                    && it.customerRating >= resInput.customerRating
                    && it.distance <= resInput.distance
                    && it.price <= resInput.price
        }.map { it.copy(cuisine = cuisine!!) }

    }

}