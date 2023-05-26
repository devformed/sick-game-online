package com.devformed.sickgame.model

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

/**
 * @author Anton Gorokh
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class TimedEntity(

	@CreatedDate
	@Column(nullable = true, updatable = false)
	@Convert(converter = Jsr310JpaConverters.InstantConverter::class)
	open var createdAt: Instant? = null,

	@LastModifiedDate
	@Column(nullable = true)
	@Convert(converter = Jsr310JpaConverters.InstantConverter::class)
	open var updatedAt: Instant? = null

) : AbstractEntity()