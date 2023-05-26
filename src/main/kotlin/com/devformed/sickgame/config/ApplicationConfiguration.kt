package com.devformed.sickgame.config

import com.devformed.sickgame.service.storage.LocalFileStorageProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


/**
 * @author Anton Gorokh
 */
@Configuration
@ComponentScan("com.devformed.sickgame")
@EnableConfigurationProperties(LocalFileStorageProperties::class)
class ApplicationConfiguration {

	@Bean
	fun passwordEncoder(): PasswordEncoder {
		return BCryptPasswordEncoder()
	}
}
