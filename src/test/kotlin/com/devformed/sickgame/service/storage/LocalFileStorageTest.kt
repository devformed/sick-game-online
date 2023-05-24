package com.devformed.sickgame.service.storage

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 * @author Anton Gorokh
 */
@SpringBootTest
class LocalFileStorageTest(@Autowired storageProperties: LocalFileStorageProperties) {

	private val storage: FileStorage

	init {
		storage = LocalFileStorage(storageProperties)
	}

	@Test
	fun uploadTempFile_ok() {
		assertTrue(uploadSampleTempFile("txt").endsWith(".txt"))
		assertEquals(-1, uploadSampleTempFile("").indexOf("."))
		assertEquals(-1, uploadSampleTempFile(" ").indexOf("."))
		assertEquals(-1, uploadSampleTempFile(null).indexOf("."))
	}

	@Test
	fun uploadTempFile_sameFileDifferentNames() {
		val fileName1 = uploadSampleTempFile("txt")
		val fileName2 = uploadSampleTempFile("txt")
		assertNotEquals(fileName1, fileName2)
	}

	@Test
	fun getTempFile_ok() {
		val fileName = uploadSampleTempFile("txt")
		val fileContent = storage.getTempFileContent(fileName)
		assertArrayEquals("uploadTempFile_ok".toByteArray(), fileContent)
	}

	@Test
	fun getTempFile_fileNotExists() {
		val tempFileContent = storage.getTempFileContent("notExistingFile.txt")
		assertNull(tempFileContent)
	}

	@Test
	fun deleteTempFile_exists() {
		val fileName = uploadSampleTempFile("txt")
		assertTrue(storage.deleteTempFile(fileName))
	}

	@Test
	fun deleteTempFile_notExists() {
		assertFalse(storage.deleteTempFile("notExistingFile.txt"))
	}

	private fun uploadSampleTempFile(fileExtension: String?): String {
		val fileContent = "uploadTempFile_ok".toByteArray()
		return storage.uploadTempFileContent(fileContent, fileExtension)!!
	}
}