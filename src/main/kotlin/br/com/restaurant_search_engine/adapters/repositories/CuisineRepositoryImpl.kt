package br.com.restaurant_search_engine.adapters.repositories

import br.com.restaurant_search_engine.domain.entities.Cuisine
import br.com.restaurant_search_engine.domain.ports.out.CuisineRepository
import br.com.restaurant_search_engine.helper.CsvFileReader
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class CuisineRepositoryImpl : CuisineRepository {

    private val logger = LoggerFactory.getLogger(javaClass)

    companion object {
        const val FILENAME = "cuisines"
    }

    override fun getAllCuisines(): List<Cuisine> {
        logger.info("getting all cuisines")
        val cuisines = CsvFileReader().readCsvFile<Cuisine>(FILENAME)
        return cuisines
    }

    override fun getCuisineById(cuisineId: Int): Cuisine? {
        logger.info("getting cuisine with id: {}", cuisineId)

        val cuisine = this.getAllCuisines()
            .find { it.id == cuisineId }

        if (cuisine == null) {
            logger.warn("Could not find cuisine with id: {}", cuisineId)
        }

        return cuisine
    }
}