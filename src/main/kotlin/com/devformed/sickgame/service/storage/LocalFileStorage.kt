package com.devformed.sickgame.service.storage

import org.apache.commons.lang3.StringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.util.UUID

/**
 * @author Anton Gorokh
 */
@Component
class LocalFileStorage @Autowired constructor(private val properties: LocalFileStorageProperties) : FileStorage {

	private val log: Logger = LoggerFactory.getLogger(this::class.java)

	override fun uploadTempFileContent(fileContent: ByteArray, fileExtension: String?): String? {
		var fileName = UUID.randomUUID().toString()
		if (StringUtils.isNotBlank(fileExtension)) {
			fileName += ".$fileExtension"
		}

		val uploadResult = uploadFile(properties.tempDirectoryPath, fileName, fileContent)
		return if (uploadResult) fileName else null
	}

	override fun getTempFileContent(filePath: String): ByteArray? {
		return getFileContent(properties.tempDirectoryPath, filePath)
	}

	override fun deleteTempFile(filePath: String): Boolean {
		return deleteFile(properties.tempDirectoryPath, filePath)
	}

	private fun uploadFile(accessDirectory: String, filePath: String, content: ByteArray): Boolean {
		val absolutePath = buildAbsoluteFilePath(accessDirectory, filePath)
		if (absolutePath.toFile().exists()) {
			throw IllegalArgumentException("The provided file path already exists")
		}

		ensureDirsOnPath(absolutePath)
		return try {
			Files.write(absolutePath, content)
			true
		} catch (e: IOException) {
			log.error("Failed to upload file with path $absolutePath", e)
			false
		}
	}

	private fun getFileContent(accessDirectory: String, filePath: String): ByteArray? {
		val absolutePath = buildAbsoluteFilePath(accessDirectory, filePath)

		return try {
			Files.readAllBytes(buildAbsoluteFilePath(accessDirectory, filePath))
		} catch (e: IOException) {
			log.error("Failed to get file with path $absolutePath", e)
			null
		}
	}

	private fun deleteFile(accessDirectory: String, filePath: String): Boolean {
		val absolutePath = buildAbsoluteFilePath(accessDirectory, filePath)

		return try {
			Files.deleteIfExists(absolutePath)
		} catch (e: IOException) {
			log.error("Failed to delete file with path $absolutePath", e)
			false
		}
	}

	private fun buildAbsoluteFilePath(accessDirectory: String, filePath: String): Path {
		return File(properties.baseDirectoryPath + "/" + accessDirectory + "/" + filePath).toPath()
	}

	private fun ensureDirsOnPath(filePath: Path) {
		val parentDirectory = filePath.parent.toFile()
		parentDirectory.mkdirs();
	}
}