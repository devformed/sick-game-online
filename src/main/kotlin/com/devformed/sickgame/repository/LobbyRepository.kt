package com.devformed.sickgame.repository

import com.devformed.sickgame.model.Lobby
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Anton Gorokh
 */
interface LobbyRepository : JpaRepository<Lobby, Long> {
	fun findByName(name: String): Lobby
}