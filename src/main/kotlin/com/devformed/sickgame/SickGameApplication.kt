package com.devformed.sickgame

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author Anton Gorokh
 */
@SpringBootApplication
class SickGameApplication

fun main(args: Array<String>) {
	runApplication<SickGameApplication>(*args)
}
