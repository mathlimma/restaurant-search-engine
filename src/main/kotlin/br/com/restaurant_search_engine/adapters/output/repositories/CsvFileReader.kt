package br.com.restaurant_search_engine.adapters.output.repositories

import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import java.io.FileReader

class CsvFileReader {

    val csvMapper = CsvMapper()

    inline fun <reified T> readCsvFile(fileName: String): List<T> {
        val filesPath = "files/$fileName"
        FileReader(filesPath).use { reader ->
            return csvMapper
                .readerFor(T::class.java)
                .with(CsvSchema.emptySchema().withHeader())
                .readValues<T>(reader)
                .readAll()
                .toList()
        }
    }
}