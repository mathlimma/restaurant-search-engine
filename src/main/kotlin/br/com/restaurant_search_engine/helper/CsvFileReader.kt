package br.com.restaurant_search_engine.helper

import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.slf4j.LoggerFactory
import java.io.InputStream


class CsvFileReader {
    val logger = LoggerFactory.getLogger(javaClass)

    val csvMapper = CsvMapper().registerKotlinModule()

    inline fun <reified T : Any> readCsvFile(fileName: String): List<T> {
        logger.debug("File to read: {}", fileName)

        val resourcePath = "files/$fileName.csv"
        val inputStream: InputStream = javaClass.classLoader.getResourceAsStream(resourcePath)
            ?: throw IllegalArgumentException("Resource not found: $resourcePath")

        logger.debug("Reading file from classpath: {}", resourcePath)

        inputStream.use { stream ->
            return csvMapper
                .readerFor(T::class.java)
                .with(CsvSchema.emptySchema().withHeader())
                .readValues<T>(stream)
                .readAll()
                .toList()
        }
    }

}