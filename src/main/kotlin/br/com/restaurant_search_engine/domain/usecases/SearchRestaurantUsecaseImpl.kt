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

    override fun search(restaurantSearched: Restaurant): List<Restaurant> {
        logger.info("starting search - restaurant searched params: {}", restaurantSearched)

        return this.restaurantRepository.getAllRestaurants()
    }

    private fun handleFilter(restaurantList: List<Restaurant>, res: Restaurant): List<Restaurant> {
        logger.debug("filtering by restaurant : {}", res)

        // revisit order
        return restaurantList.filter {
             it.cuisine.id == res.cuisine.id
             && it.name.contains(res.name)
             && it.customerRating >= res.customerRating
             && it.distance <= res.distance
             && it.price <= res.price
        }
    }

}