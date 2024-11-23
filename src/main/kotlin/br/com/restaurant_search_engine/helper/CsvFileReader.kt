package br.com.restaurant_search_engine.helper

import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.InputStream

@Component
class CsvFileReader {

    private val logger = LoggerFactory.getLogger(CsvFileReader::class.java)

    fun <T : Any> readCsvFile(fileName: String, clazz: Class<T>): List<T> {
        val csvMapper = CsvMapper().registerKotlinModule()

        logger.debug("File to read: {}", fileName)

        val resourcePath = "files/$fileName.csv"
        val inputStream: InputStream = javaClass.classLoader.getResourceAsStream(resourcePath)
            ?: throw IllegalArgumentException("Resource not found: $resourcePath")

        logger.debug("Reading file from classpath: {}", resourcePath)

        inputStream.use { stream ->
            return csvMapper
                .readerFor(clazz)
                .with(CsvSchema.emptySchema().withHeader())
                .readValues<T>(stream)
                .readAll()
                .toList()
        }
    }

}