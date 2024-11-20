package br.com.restaurant_search_engine.adapters.controllers

import br.com.restaurant_search_engine.adapters.dto.input.RestaurantInputDTO
import br.com.restaurant_search_engine.adapters.dto.output.RestaurantOutputDTO
import br.com.restaurant_search_engine.domain.ports.`in`.SearchRestaurantUsecase
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/search")
class PaymentController(private val searchRestaurantUsecase: SearchRestaurantUsecase) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun search(
        @Valid @RequestBody requestBody: RestaurantInputDTO
    ): List<RestaurantOutputDTO> {
        logger.info("received new request: {}", requestBody)
        return searchRestaurantUsecase.search()
    }

}