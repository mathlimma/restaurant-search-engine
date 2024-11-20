package br.com.restaurant_search_engine.adapters.repositories

import br.com.restaurant_search_engine.domain.entities.Cuisine
import br.com.restaurant_search_engine.domain.ports.out.CuisineRepository
import org.slf4j.LoggerFactory

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

    override fun getCuisinesById(cuisineId: Int): Cuisine {
        logger.info("getting cuisine with id: {}", cuisineId)

        // TODO (this.getAllCuisines)

        logger.warn("could not find cuisine with id: {}", cuisineId)

    }
}