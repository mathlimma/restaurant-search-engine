package br.com.restaurant_search_engine.adapters.repositories

import br.com.restaurant_search_engine.adapters.repositories.CuisineRepositoryImpl.Companion.FILENAME
import br.com.restaurant_search_engine.adapters.repositories.dao.CuisineDAO
import br.com.restaurant_search_engine.domain.entities.Cuisine
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
class CuisineRepositoryImplTest {

    @Mock
    private lateinit var csvFileReader: CsvFileReader

    @InjectMocks
    private lateinit var cuisineRepository: CuisineRepositoryImpl

    @BeforeEach
    fun setup() {
        cuisineRepository.javaClass.getDeclaredField("cuisines").apply {
            isAccessible = true
            set(cuisineRepository, null)
        }
    }


    @Test
    fun `should load all cuisines from CSV file when not cached`() {
        `when`(csvFileReader.readCsvFile(FILENAME, CuisineDAO::class.java)).thenReturn(Utils.mockCuisines)

        val cuisines = cuisineRepository.getAllCuisines()

        assertThat(cuisines).hasSize(3)
        assertThat(cuisines).containsExactlyInAnyOrder(
            Cuisine(id = 1, name = "Italian"),
            Cuisine(id = 2, name = "Japanese"),
            Cuisine(id = 3, name = "Mexican")
        )
        verify(csvFileReader, times(1)).readCsvFile(FILENAME, CuisineDAO::class.java)
    }

    @Test
    fun `should not reload cuisines if already cached`() {
        `when`(csvFileReader.readCsvFile(FILENAME, CuisineDAO::class.java)).thenReturn(Utils.mockCuisines)
        cuisineRepository.getAllCuisines() // Load once to populate the cache

        val cuisines = cuisineRepository.getAllCuisines() // Second call should use the cache

        assertThat(cuisines).hasSize(3)
        verify(csvFileReader, times(1)).readCsvFile(FILENAME, CuisineDAO::class.java)
    }

    @Test
    fun `should return cuisines by name`() {
        `when`(csvFileReader.readCsvFile(FILENAME, CuisineDAO::class.java)).thenReturn(Utils.mockCuisines)

        val cuisines = cuisineRepository.getCuisinesByName("Japanese")

        assertThat(cuisines).isNotNull
        assertThat(cuisines.first().id).isEqualTo(2)
        assertThat(cuisines.first().name).isEqualTo("Japanese")
    }

    @Test
    fun `should return null if cuisine name does not exist`() {
        `when`(csvFileReader.readCsvFile(FILENAME, CuisineDAO::class.java)).thenReturn(Utils.mockCuisines)

        val cuisine = cuisineRepository.getCuisinesByName("Korean")

        assertThat(cuisine).isEmpty()
    }

    @Test
    fun `should handle case-insensitive cuisine name lookup`() {
        `when`(csvFileReader.readCsvFile(FILENAME, CuisineDAO::class.java)).thenReturn(Utils.mockCuisines)

        val cuisines = cuisineRepository.getCuisinesByName("jApAnEsE")

        assertThat(cuisines).isNotNull
        assertThat(cuisines.first().id).isEqualTo(2)
        assertThat(cuisines.first().name).isEqualTo("Japanese")
    }
}
