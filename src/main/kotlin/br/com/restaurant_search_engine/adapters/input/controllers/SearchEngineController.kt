package br.com.restaurant_search_engine.adapters.input.controllers

import br.com.restaurant_search_engine.adapters.input.dto.RestaurantInputDTO
import br.com.restaurant_search_engine.domain.entities.Restaurant
import br.com.restaurant_search_engine.domain.ports.`in`.SearchRestaurantUsecase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/search")
class PaymentController(private val searchRestaurantUsecase: SearchRestaurantUsecase) {

    @PostMapping
    fun search(
        @RequestBody requestBody: RestaurantInputDTO
    ): List<Restaurant> {
        return searchRestaurantUsecase.search()
    }


}