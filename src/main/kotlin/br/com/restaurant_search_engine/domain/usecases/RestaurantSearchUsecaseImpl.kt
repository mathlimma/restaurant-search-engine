package br.com.restaurant_search_engine.domain.usecases

import br.com.restaurant_search_engine.domain.entities.Cuisine
import br.com.restaurant_search_engine.domain.entities.Restaurant
import br.com.restaurant_search_engine.domain.ports.`in`.RestaurantSearchUsecase
import br.com.restaurant_search_engine.domain.ports.out.CuisineRepository
import br.com.restaurant_search_engine.domain.ports.out.RestaurantRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class RestaurantSearchUsecaseImpl(
    private val restaurantRepository: RestaurantRepository,
    private val cuisineRepository: CuisineRepository
) : RestaurantSearchUsecase {

    private val logger = LoggerFactory.getLogger(javaClass)

    companion object {
        const val RESTAURANT_SEARCH_LIMIT = 5
    }

    override fun search(restaurantInput: Restaurant): List<Restaurant> {
        logger.info("starting search - restaurant searched params: {}", restaurantInput)

        val cuisines = this.cuisineRepository.getCuisinesByName(cuisineName = restaurantInput.cuisine.name!!)

        if (cuisines.isEmpty()) {
            logger.warn("could not find any cuisines by : {}", restaurantInput.cuisine.name)
            throw IllegalArgumentException("could not find any cuisines by: ${restaurantInput.cuisine.name}")
        }

        val restaurants = this.restaurantRepository.getAllRestaurants()

        val filteredRestaurants =
            this.handleFilter(restaurants = restaurants, cuisines = cuisines, resInput = restaurantInput)
        logger.info("filtered restaurants: {}", filteredRestaurants)

        return this.handleSortingAndLimiting(restaurants = filteredRestaurants).also {
            logger.info("sorted restaurants: {}", it)
        }
    }

    private fun handleFilter(
        restaurants: List<Restaurant>,
        cuisines: List<Cuisine>,
        resInput: Restaurant
    ): List<Restaurant> {
        logger.debug("filtering by restaurant input: {}", resInput)

        return restaurants.filter {
            it.customerRating >= resInput.customerRating
                    && it.distance <= resInput.distance
                    && it.price <= resInput.price
                    && it.name.lowercase().contains(resInput.name.lowercase())
                    && cuisines.any { cuisine -> cuisine.id == it.cuisine.id }
        }.map { restaurant ->
            val matchedCuisine = cuisines.find { cuisine -> cuisine.id == restaurant.cuisine.id }
            matchedCuisine?.let { restaurant.copy(cuisine = matchedCuisine) } ?: restaurant
        }
    }

    private fun handleSortingAndLimiting(
        restaurants: List<Restaurant>
    ): List<Restaurant> {
        logger.debug("sorting restaurants: {}", restaurants)

        return restaurants.sortedWith(
            compareBy<Restaurant> { it.distance }
                .thenByDescending { it.customerRating }
                .thenBy { it.price }
        )
            .take(RESTAURANT_SEARCH_LIMIT)
    }

}