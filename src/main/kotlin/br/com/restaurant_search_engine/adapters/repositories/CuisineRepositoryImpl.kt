package br.com.restaurant_search_engine.adapters.repositories

import br.com.restaurant_search_engine.adapters.repositories.dao.CuisineDAO
import br.com.restaurant_search_engine.domain.entities.Cuisine
import br.com.restaurant_search_engine.domain.ports.out.CuisineRepository
import br.com.restaurant_search_engine.helper.CsvFileReader
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class CuisineRepositoryImpl(private val csvFileReader: CsvFileReader) : CuisineRepository {

    private val logger = LoggerFactory.getLogger(javaClass)
    private var cuisines: List<CuisineDAO>? = null

    companion object {
        const val FILENAME = "cuisines"
    }

    override fun getAllCuisines(): List<Cuisine> {
        logger.info("getting all cuisines")
        when (this.cuisines) {
            null -> {
                val cuisineList = csvFileReader.readCsvFile(FILENAME, CuisineDAO::class.java)
                this.cuisines = cuisineList
            }

            else -> logger.debug("csv file wont be loaded - cache hit: {}", this.cuisines)
        }
        return this.cuisines!!.map { it.toDomain() }
    }

    override fun getCuisinesByName(cuisineName: String): List<Cuisine> {
        logger.info("getting cuisines by name: {}", cuisineName)

        val cuisines = this.getAllCuisines()
            .filter { it.name!!.lowercase().contains(cuisineName.lowercase()) }

        return cuisines.also { logger.info("cuisines : {}", cuisineName) }
    }
}