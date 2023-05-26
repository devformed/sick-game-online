package com.devformed.sickgame

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * @author Anton Gorokh
 */
@SpringBootApplication
class SickGameApplication : WebMvcConfigurer

fun main(args: Array<String>) {
	runApplication<SickGameApplication>(*args)
}
