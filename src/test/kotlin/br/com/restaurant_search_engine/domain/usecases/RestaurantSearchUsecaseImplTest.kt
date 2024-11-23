package br.com.restaurant_search_engine.domain.usecases

import br.com.restaurant_search_engine.domain.entities.Cuisine
import br.com.restaurant_search_engine.domain.entities.Restaurant
import br.com.restaurant_search_engine.domain.ports.out.CuisineRepository
import br.com.restaurant_search_engine.domain.ports.out.RestaurantRepository
import br.com.restaurant_search_engine.testUtils.Utils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class RestaurantSearchUsecaseImplTest {

    private val restaurantRepository: RestaurantRepository = mock()
    private val cuisineRepository: CuisineRepository = mock()

    private val usecase = RestaurantSearchUsecaseImpl(restaurantRepository, cuisineRepository)

    private lateinit var cuisine: Cuisine
    private lateinit var allRestaurants: List<Restaurant>
    private lateinit var inputRestaurant: Restaurant

    @BeforeEach
    fun setup() {
        cuisine = Utils.italianCuisine
        allRestaurants = Utils.allRestaurants
        inputRestaurant = Utils.inputRestaurant
    }

    @Test
    fun `should return filtered and sorted restaurants when all criteria match`() {

        whenever(cuisineRepository.getCuisineByName("Italian")).thenReturn(cuisine)
        whenever(restaurantRepository.getAllRestaurants()).thenReturn(allRestaurants)

        val result = usecase.search(inputRestaurant)

        assertThat(result).hasSize(4)
        assertThat(result[0].name).isEqualTo("Spoleto")
        assertThat(result[1].name).isEqualTo("La Braza")

        verify(cuisineRepository).getCuisineByName("Italian")
        verify(restaurantRepository).getAllRestaurants()
    }

    @Test
    fun `should throw exception when cuisine is not found`() {

        inputRestaurant = inputRestaurant.copy(cuisine = Cuisine(name = "Unknown"))

        whenever(cuisineRepository.getCuisineByName("Unknown")).thenReturn(null)

        val exception = assertThrows(IllegalArgumentException::class.java) {
            usecase.search(inputRestaurant)
        }

        assertThat(exception.message).isEqualTo("could not find cuisine: Unknown")
        verify(cuisineRepository).getCuisineByName("Unknown")
        verifyNoInteractions(restaurantRepository)
    }

    @Test
    fun `should return an empty list when no restaurants match criteria`() {
        inputRestaurant = inputRestaurant.copy(name = "MacDonald's")

        whenever(cuisineRepository.getCuisineByName("Italian")).thenReturn(cuisine)
        whenever(restaurantRepository.getAllRestaurants()).thenReturn(allRestaurants)

        val result = usecase.search(inputRestaurant)

        assertThat(result).isEmpty()
        verify(cuisineRepository).getCuisineByName("Italian")
        verify(restaurantRepository).getAllRestaurants()
    }

    @Test
    fun `should limit results to RESTAURANT_SEARCH_LIMIT`() {

        inputRestaurant = inputRestaurant.copy(name = "")

        val allRestaurants = (1..10).map {
            Restaurant(name = "Restaurant", cuisine = cuisine, customerRating = 5.0, distance = 5.0, price = 10.0)
        }

        whenever(cuisineRepository.getCuisineByName("Italian")).thenReturn(cuisine)
        whenever(restaurantRepository.getAllRestaurants()).thenReturn(allRestaurants)

        val result = usecase.search(inputRestaurant)

        assertThat(result).hasSize(RestaurantSearchUsecaseImpl.RESTAURANT_SEARCH_LIMIT)
        verify(cuisineRepository).getCuisineByName("Italian")
        verify(restaurantRepository).getAllRestaurants()
    }
}
