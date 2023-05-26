package com.devformed.sickgame.dto

/**
 * @author Anton Gorokh
 */
data class LobbyDto (
	val name: String,
	val password: String?,
	val maxPlayers: Int
)