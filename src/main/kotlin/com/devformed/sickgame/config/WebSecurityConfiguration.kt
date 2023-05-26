package com.devformed.sickgame.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

/**
 * @author Anton Gorokh
 */
@Configuration
@EnableWebSecurity
class WebSecurityConfiguration {

	@Bean
	fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
		http.authorizeHttpRequests { authorizeRequests ->
			authorizeRequests
				.anyRequest().permitAll()
		}
		return http.build()
	}
}