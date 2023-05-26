package com.devformed.sickgame

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.PropertySource


/**
 * @author Anton Gorokh
 */
@ComponentScan("com.devformed.sickgame")
@PropertySource("classpath:application.yaml")
class TestConfiguration {

	@Bean
	fun objectMapper(): ObjectMapper {
		val objectMapper = ObjectMapper()
		objectMapper.registerModule(JavaTimeModule())
		return objectMapper
	}
}