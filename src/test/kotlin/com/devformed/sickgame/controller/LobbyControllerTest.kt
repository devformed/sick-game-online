package com.devformed.sickgame.controller

import com.devformed.sickgame.dto.LobbyDto
import com.devformed.sickgame.repository.LobbyRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


/**
 * @author Anton Gorokh
 */
@SpringBootTest
@AutoConfigureMockMvc
class LobbyControllerTest {

	@Autowired
	private lateinit var mvc: MockMvc

	@Autowired
	private lateinit var objectMapper: ObjectMapper

	@Autowired
	private lateinit var lobbyRepository: LobbyRepository

	@Test
	fun createLobby_noPassword() {
		// given
		val lobbyDto = getSampleLobbyDto()
		val request = post("/lobby")
			.content(objectMapper.writeValueAsString(getSampleLobbyDto()))
			.contentType(MediaType.APPLICATION_JSON_VALUE)

		// when
		val result: MvcResult = mvc.perform(request)
			.andExpect(status().isOk())
			.andReturn()

		// then
		val lobbyId: Long = result.response.contentAsString.toLong()
	}

	@Test
	fun createLobby_withPassword() {

	}

	private fun getSampleLobbyDto(): LobbyDto =
		LobbyDto("lobby of cool guys", null, 5)
}
