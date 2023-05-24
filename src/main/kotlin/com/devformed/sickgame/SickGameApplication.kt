package com.devformed.sickgame

import com.devformed.sickgame.service.storage.LocalFileStorageProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

/**
 * @author Anton Gorokh
 */
@EnableConfigurationProperties(LocalFileStorageProperties::class)
@SpringBootApplication(scanBasePackages = ["com.devformed.sickgame"])
class SickGameApplication

fun main(args: Array<String>) {
	runApplication<SickGameApplication>(*args)
}
