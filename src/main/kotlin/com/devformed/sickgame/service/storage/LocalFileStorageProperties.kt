package com.devformed.sickgame.service.storage

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * @author Anton Gorokh
 */
@ConfigurationProperties(prefix = "com.devformed.sickgame.file-storage.local")
data class LocalFileStorageProperties(
	val baseDirectoryPath: String,
	val tempDirectoryPath: String
)