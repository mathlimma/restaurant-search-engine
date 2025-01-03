package br.com.restaurant_search_engine.adapters.controllers

import br.com.restaurant_search_engine.adapters.controllers.dto.input.RestaurantInputDTO
import br.com.restaurant_search_engine.adapters.controllers.dto.output.RestaurantOutputDTO
import br.com.restaurant_search_engine.adapters.controllers.dto.output.toOutputDTO
import br.com.restaurant_search_engine.domain.ports.`in`.RestaurantSearchUsecase
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/search")
class SearchEngineController(private val restaurantSearchUsecase: RestaurantSearchUsecase) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun search(
        @Valid @RequestBody requestBody: RestaurantInputDTO
    ): List<RestaurantOutputDTO> {
        logger.info("received new request: {}", requestBody)
        val results = restaurantSearchUsecase.search(restaurantInput = requestBody.toDomain())
            .map { it.toOutputDTO() }

        logger.info("search results to be returned: {}", results)
        return results
    }

}