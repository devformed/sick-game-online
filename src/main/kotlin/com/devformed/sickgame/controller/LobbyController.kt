package com.devformed.sickgame.controller

import com.devformed.sickgame.dto.LobbyDto
import com.devformed.sickgame.service.LobbyService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Anton Gorokh
 */
@RestController
@RequestMapping("/lobby")
class LobbyController(private val service: LobbyService) {

	@PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
	fun createLobby(@RequestBody lobbyDto: LobbyDto): Long {
		return service.createLobby(lobbyDto)
	}
}