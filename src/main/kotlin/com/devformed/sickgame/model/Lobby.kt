package com.devformed.sickgame.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.Size

/**
 * @author Anton Gorokh
 */
@Entity
@Table
open class Lobby(

	@Column(nullable = false)
	open var name: String? = null,

	open var password: String? = null,

	@Column(nullable = false)
	@Size(min = 3, max = 6)
	open var maxPlayers: Int? = null,

	@OneToMany(mappedBy = "lobby")
	open var packs: MutableList<LobbyPack> = arrayListOf()

) : AbstractEntity()

