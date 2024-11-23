package br.com.restaurant_search_engine.adapters.repositories

import br.com.restaurant_search_engine.adapters.repositories.dao.RestaurantDAO
import br.com.restaurant_search_engine.domain.entities.Cuisine
import br.com.restaurant_search_engine.domain.entities.Restaurant
import br.com.restaurant_search_engine.helper.CsvFileReader
import br.com.restaurant_search_engine.testUtils.Utils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class RestaurantRepositoryImplTest {

    @Mock
    private lateinit var csvFileReader: CsvFileReader

    @InjectMocks
    private lateinit var restaurantRepository: RestaurantRepositoryImpl

    @BeforeEach
    fun setup() {
        restaurantRepository.javaClass.getDeclaredField("restaurants").apply {
            isAccessible = true
            set(restaurantRepository, null)
        }
    }

    @Test
    fun `should load all restaurants from CSV file when not cached`() {

        `when`(csvFileReader.readCsvFile("restaurants", RestaurantDAO::class.java)).thenReturn(Utils.allRestaurantsDAO)

        val restaurants = restaurantRepository.getAllRestaurants()

        assertThat(restaurants).hasSize(3)
        assertThat(restaurants).containsExactlyInAnyOrder(
            Restaurant(
                name = "Pizza Place",
                customerRating = 4.0,
                distance = 5.0,
                price = 15.0,
                cuisine = Cuisine(id = 1, name = null)
            ),
            Restaurant(
                name = "Pizza House",
                customerRating = 5.0,
                distance = 3.0,
                price = 10.0,
                cuisine = Cuisine(id = 2, name = null)
            ),
            Restaurant(
                name = "Burger Joint",
                customerRating = 4.0,
                distance = 6.0,
                price = 10.0,
                cuisine = Cuisine(id = 3, name = null)
            )
        )
        verify(csvFileReader, times(1)).readCsvFile("restaurants", RestaurantDAO::class.java)
    }

    @Test
    fun `should not reload restaurants if already cached`() {

        `when`(csvFileReader.readCsvFile("restaurants", RestaurantDAO::class.java)).thenReturn(Utils.allRestaurantsDAO)

        restaurantRepository.getAllRestaurants() // First call to populate cache
        val restaurants = restaurantRepository.getAllRestaurants() // Second call should use cache

        assertThat(restaurants).hasSize(3)
        verify(csvFileReader, times(1)).readCsvFile("restaurants", RestaurantDAO::class.java)
    }
}
