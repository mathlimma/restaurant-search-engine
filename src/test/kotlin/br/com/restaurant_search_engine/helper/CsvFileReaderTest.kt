package br.com.restaurant_search_engine.helper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

data class TestData(val name: String, val age: Int, val city: String)

class CsvFileReaderTest {

    private val csvFileReader = CsvFileReader()

    @Test
    fun `should read and parse CSV file correctly`() {

        val result: List<TestData> = csvFileReader.readCsvFile("test_data")

        assertThat(result).hasSize(2)
        assertThat(result[0]).isEqualTo(TestData(name = "Alice", age = 30, city = "New York"))
        assertThat(result[1]).isEqualTo(TestData(name = "Bob", age = 25, city = "San Francisco"))

    }

    @Test
    fun `should throw exception when file is not found`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            csvFileReader.readCsvFile<TestData>("non_existent_file")
        }

        assertThat(exception.message).isEqualTo("Resource not found: files/non_existent_file.csv")
    }

}
