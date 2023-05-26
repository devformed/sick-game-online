package com.devformed.sickgame.controller

import com.devformed.sickgame.dto.LobbyDto
import com.devformed.sickgame.repository.LobbyRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional


/**
 * @author Anton Gorokh
 */
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class LobbyControllerTest {

	@Autowired
	private lateinit var mvc: MockMvc

	@Autowired
	private lateinit var objectMapper: ObjectMapper

	@Autowired
	private lateinit var passwordEncoder: PasswordEncoder

	@Autowired
	private lateinit var lobbyRepository: LobbyRepository

	@Test
	fun createLobby_noPassword() {
		// given
		val lobbyDto = LobbyDto("lobby of cool guys", null, 5)
		val request = post("/lobby")
			.content(objectMapper.writeValueAsString(lobbyDto))
			.contentType(MediaType.APPLICATION_JSON_VALUE)

		// when
		val result: MvcResult = mvc.perform(request)
			.andExpect(status().isOk)
			.andReturn()

		// then
		val lobbyId: Long = result.response.contentAsString.toLong()
		val lobby = lobbyRepository.getReferenceById(lobbyId)

		assertEquals(lobbyDto.name, lobby.name)
		assertEquals(null, lobby.password)
		assertEquals(lobbyDto.maxPlayers, lobby.maxPlayers)
	}

	@Test
	fun createLobby_withPassword() {
		// given
		val lobbyDto = LobbyDto("lobby of cool guys", "12345", 5)
		val request = post("/lobby")
			.content(objectMapper.writeValueAsString(lobbyDto))
			.contentType(MediaType.APPLICATION_JSON_VALUE)

		// when
		val result: MvcResult = mvc.perform(request)
			.andExpect(status().isOk)
			.andReturn()

		// then
		val lobbyId: Long = result.response.contentAsString.toLong()
		val lobby = lobbyRepository.getReferenceById(lobbyId)

		assertEquals(lobbyDto.name, lobby.name)
		assertTrue(passwordEncoder.matches(lobbyDto.password, lobby.password))
		assertEquals(lobbyDto.maxPlayers, lobby.maxPlayers)
	}
}
