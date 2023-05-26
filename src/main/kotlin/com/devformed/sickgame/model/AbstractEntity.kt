package com.devformed.sickgame.model

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.hibernate.id.enhanced.SequenceStyleGenerator
import java.io.Serializable

/**
 * @author Anton Gorokh
 */
@MappedSuperclass
abstract class AbstractEntity(

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence_gen")
	@GenericGenerator(
		name = "id_sequence_gen",
		strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		parameters = [
			Parameter(name = SequenceStyleGenerator.CONFIG_SEQUENCE_PER_ENTITY_SUFFIX, value = "id_seq_"),
			Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1")
		]
	)
	open var id: Long? = null

) : Serializable, Comparable<AbstractEntity> {

	override fun compareTo(other: AbstractEntity): Int {
		return compareValuesBy(this, other, AbstractEntity::id)
	}
}