package br.com.restaurant_search_engine.adapters.output.repositories

import br.com.restaurant_search_engine.domain.entities.Cuisine
import br.com.restaurant_search_engine.domain.ports.out.CuisineRepository

class CuisineRepositoryImpl : CuisineRepository {

    companion object {
        const val FILENAME = "cuisines"
    }

    override fun getAllCuisines(): List<Cuisine> {
        val cuisines = CsvFileReader().readCsvFile<Cuisine>(FILENAME)

        return cuisines
    }

    override fun getCuisinesById(cuisineId: Int): Cuisine {
        // TODO (this.getAllCuisines)
    }
}