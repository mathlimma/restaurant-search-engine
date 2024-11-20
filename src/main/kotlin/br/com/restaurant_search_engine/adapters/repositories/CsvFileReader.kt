package br.com.restaurant_search_engine.adapters.repositories

import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import org.slf4j.LoggerFactory
import java.io.FileReader

class CsvFileReader {
    val logger = LoggerFactory.getLogger(javaClass)

    val csvMapper = CsvMapper()

    inline fun <reified T> readCsvFile(fileName: String): List<T> {
        val filesPath = "files/$fileName"
        logger.debug("reading file in: {}", filesPath)

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