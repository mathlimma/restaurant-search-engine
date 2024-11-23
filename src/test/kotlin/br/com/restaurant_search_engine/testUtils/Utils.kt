package br.com.restaurant_search_engine.testUtils

import br.com.restaurant_search_engine.adapters.controllers.dto.input.RestaurantInputDTO
import br.com.restaurant_search_engine.adapters.repositories.dao.CuisineDAO
import br.com.restaurant_search_engine.adapters.repositories.dao.RestaurantDAO
import br.com.restaurant_search_engine.domain.entities.Cuisine
import br.com.restaurant_search_engine.domain.entities.Restaurant

class Utils {

    companion object {
        val italianCuisine = Cuisine(id = 1, name = "Italian")
        val inputRestaurant = Restaurant(
            name = "",
            cuisine = Cuisine(name = "Italian"),
            customerRating = 2.0,
            distance = 10.0,
            price = 50.0
        )
        val allRestaurants = listOf(
            Restaurant(name = "Pizza Place", cuisine = italianCuisine, customerRating = 4.0, distance = 5.0, price = 15.0),
            Restaurant(name = "Pizza House", cuisine = italianCuisine, customerRating = 5.0, distance = 3.0, price = 10.0),
            Restaurant(
                name = "Burger Joint",
                cuisine = Cuisine(id = 2, name = "American"),
                customerRating = 4.0,
                distance = 6.0,
                price = 10.0
            ),
            Restaurant(
                name = "La Braza",
                cuisine = italianCuisine,
                customerRating = 5.0,
                distance = 2.0,
                price = 50.0
            ),
            Restaurant(
                name = "Bucca Tratoria",
                cuisine = italianCuisine,
                customerRating = 1.0,
                distance = 8.0,
                price = 35.0
            ),
            Restaurant(
                name = "Spoleto",
                cuisine = italianCuisine,
                customerRating = 5.0,
                distance = 2.0,
                price = 20.0
            )
        )

        val input = RestaurantInputDTO(
            name = "Pizza Place",
            cuisine = "Italian",
            customerRating = 4.0,
            distance = 5.0,
            price = 15.1
        )

        val mockCuisines = listOf(
            CuisineDAO(id = 1, name = "Italian"),
            CuisineDAO(id = 2, name = "Japanese"),
            CuisineDAO(id = 3, name = "Mexican")
        )

        val allRestaurantsDAO = listOf(
            RestaurantDAO(name = "Pizza Place", cuisineId = 1, customerRating = 4.0, distance = 5.0, price = 15.0),
            RestaurantDAO(name = "Pizza House", cuisineId = 2, customerRating = 5.0, distance = 3.0, price = 10.0),
            RestaurantDAO(
                name = "Burger Joint",
                cuisineId = 3,
                customerRating = 4.0,
                distance = 6.0,
                price = 10.0
            )
        )
    }

}