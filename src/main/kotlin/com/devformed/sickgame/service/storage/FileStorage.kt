package com.devformed.sickgame.service.storage

/**
 * @author Anton Gorokh
 */
interface FileStorage {
	fun uploadTempFileContent(filePath: String) : Boolean
	fun getTempFileContent(filePath: String) : ByteArray
	fun deleteTempFile(filePath: String) : Boolean
}