package com.devformed.sickgame.model

import com.devformed.sickgame.dto.PackType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

/**
 * @author Anton Gorokh
 */
@Entity
@Table
open class LobbyPack(

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	open var lobby: Lobby? = null,

	@Column(nullable = false)
	open var tempFilePath: String? = null,

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	open var packType: PackType? = null

) : TimedEntity()