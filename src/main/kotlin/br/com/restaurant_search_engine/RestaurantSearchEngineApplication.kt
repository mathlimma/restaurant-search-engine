package br.com.restaurant_search_engine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RestaurantSearchEngineApplication

fun main(args: Array<String>) {
	runApplication<RestaurantSearchEngineApplication>(*args)
}
