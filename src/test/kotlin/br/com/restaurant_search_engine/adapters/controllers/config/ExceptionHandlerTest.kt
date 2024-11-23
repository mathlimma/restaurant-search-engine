package br.com.restaurant_search_engine.adapters.controllers.config

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@WebMvcTest(GlobalExceptionHandler::class)
class GlobalExceptionHandlerTest(@Autowired val mockMvc: MockMvc) {

    @Test
    fun `should handle generic Exception and return INTERNAL_SERVER_ERROR`() {

        val response = mockMvc.post("/search") {
            contentType = MediaType.APPLICATION_JSON
            content = """{}"""
        }.andExpect {
            status { isInternalServerError() }
        }.andReturn()

        val actualResponse = response.response.contentAsString

        assertThat(actualResponse).contains("An unexpected error occurred")
        assertThat(actualResponse).contains(HttpStatus.INTERNAL_SERVER_ERROR.value().toString())
    }

}
