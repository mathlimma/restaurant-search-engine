package br.com.restaurant_search_engine.domain.usecases

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

    override fun search(restaurant: Restaurant): List<Restaurant> {
        logger.info("starting search - restaurant: {}", restaurant)
        return this.restaurantRepository.getAllRestaurants()
    }
}