package com.devformed.sickgame.config

import org.hibernate.boot.model.naming.Identifier
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment

/**
 * @author Anton Gorokh
 */
class CustomPhysicalNamingStrategy : PhysicalNamingStrategyStandardImpl() {

	override fun toPhysicalTableName(logicalName: Identifier?, context: JdbcEnvironment?): Identifier {
		return convertToCustomCase(logicalName)
	}

	override fun toPhysicalColumnName(logicalName: Identifier?, context: JdbcEnvironment?): Identifier {
		return convertToCustomCase(logicalName)
	}

	private fun convertToCustomCase(identifier: Identifier?): Identifier {
		val replacement = "$1_$2"
		val newName = identifier!!.text
			.replace("([a-z])([A-Z])".toRegex(), replacement)
			.lowercase()

		return Identifier.toIdentifier(newName + "_")
	}
}