package br.com.restaurant_search_engine.adapters.controllers

import br.com.restaurant_search_engine.adapters.controllers.dto.input.RestaurantInputDTO
import br.com.restaurant_search_engine.adapters.controllers.dto.output.RestaurantOutputDTO
import br.com.restaurant_search_engine.adapters.controllers.dto.output.toOutputDTO
import br.com.restaurant_search_engine.domain.ports.`in`.RestaurantSearchUsecase
import br.com.restaurant_search_engine.testUtils.Utils
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@WebMvcTest(SearchEngineController::class)
class SearchEngineControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var restaurantSearchUsecase: RestaurantSearchUsecase

    private lateinit var output: List<RestaurantOutputDTO>
    private lateinit var input: RestaurantInputDTO

    @BeforeEach
    fun setup() {
        output = Utils.allRestaurants.map { it.toOutputDTO() }
        input = Utils.input
    }

    @Test
    fun `should return search results for valid input`() {

        `when`(restaurantSearchUsecase.search(any())).thenReturn(
            Utils.allRestaurants
        )

        mockMvc.post("/search") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(input)
        }
            .andExpect {
                status { isOk() }
                content {
                    json(objectMapper.writeValueAsString(output))
                }
            }
    }

    @Test
    fun `should return 400 for invalid input`() {
        val invalidInput = mapOf(
            "name" to "",
            "cuisine" to "",
            "customerRating" to 0,
            "distance" to 100,
            "price" to -5
        )

        mockMvc.post("/search") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(invalidInput)
        }
            .andExpect {
                status { isBadRequest() }
            }
    }

    @Test
    fun `should handle empty search results`() {

        input = input.copy(name = "Unknown")

        `when`(restaurantSearchUsecase.search(any())).thenReturn(emptyList())

        mockMvc.post("/search") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(input)
        }
            .andExpect {
                status { isOk() }
                content { json("[]") }
            }
    }
}
