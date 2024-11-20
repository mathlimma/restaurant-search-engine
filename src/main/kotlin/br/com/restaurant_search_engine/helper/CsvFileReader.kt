package br.com.restaurant_search_engine.helper

import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.slf4j.LoggerFactory
import java.io.FileReader
import java.nio.file.Paths


class CsvFileReader {
    val logger = LoggerFactory.getLogger(javaClass)

    val csvMapper = CsvMapper().registerKotlinModule()

    inline fun <reified T> readCsvFile(fileName: String): List<T> {

        logger.debug("file to read: {}", fileName)

        val url = javaClass.classLoader.getResource("files/$fileName.csv")
        val file = Paths.get(url.toURI()).toFile()
        val absolutePath = file.absolutePath

        logger.debug("reading file in: {}", absolutePath)

        FileReader(absolutePath).use { reader ->
            return csvMapper
                .readerFor(T::class.java)
                .with(CsvSchema.emptySchema().withHeader())
                .readValues<T>(reader)
                .readAll()
                .toList()
        }
    }
}