package com.devformed.sickgame.service

import com.devformed.sickgame.dto.LobbyDto
import com.devformed.sickgame.model.Lobby
import com.devformed.sickgame.repository.LobbyRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Anton Gorokh
 */
@Service
@Transactional
class LobbyService(val repository: LobbyRepository, val passwordEncoder: PasswordEncoder) {

	fun createLobby(lobbyDto: LobbyDto): Long {
		val encodedPassword = lobbyDto.password?.let { passwordEncoder.encode(it) }
		val lobby: Lobby = Lobby(lobbyDto.name, encodedPassword, lobbyDto.maxPlayers)
		return repository.save(lobby).id!!
	}
}