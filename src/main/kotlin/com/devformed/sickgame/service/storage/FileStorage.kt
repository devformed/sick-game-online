package com.devformed.sickgame.service.storage

/**
 * @author Anton Gorokh
 */
interface FileStorage {
	fun uploadTempFileContent(fileContent: ByteArray, fileExtension: String?): String?
	fun getTempFileContent(filePath: String): ByteArray?
	fun deleteTempFile(filePath: String): Boolean
}