package com.devformed.sickgame.service

import com.devformed.sickgame.dto.LobbyDto
import com.devformed.sickgame.exception.Rule
import com.devformed.sickgame.exception.Validator
import com.devformed.sickgame.model.Lobby
import com.devformed.sickgame.repository.LobbyRepository
import com.devformed.sickgame.util.Constants
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Anton Gorokh
 */
@Service
@Transactional
class LobbyService(val repository: LobbyRepository, val passwordEncoder: PasswordEncoder) {


	val lobbyValidator: Validator<LobbyDto> = Validator<LobbyDto>()
		.addRule(Rule("VALIDATION_ERROR.LOBBY.INVALID_PLAYERS_COUNT") { it.maxPlayers in 3..6 })

	fun createLobby(lobbyDto: LobbyDto): Long {
		lobbyValidator.throwIfInvalid(lobbyDto, Constants.defaultLocale)
		val encodedPassword = lobbyDto.password?.let { passwordEncoder.encode(it) }
		val lobby = Lobby(lobbyDto.name, encodedPassword, lobbyDto.maxPlayers)
		return repository.save(lobby).id!!
	}
}