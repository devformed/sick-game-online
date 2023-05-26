package com.devformed.sickgame.i18n

import java.util.Locale

/**
 * @author Anton Gorokh
 */
class I18nToken(private val token: String, private val placeholdersMap: MutableMap<String, String>) {

	constructor(token: String) : this(token, mutableMapOf())

	fun placeholder(key: String, value: String): I18nToken =
		this.apply { placeholdersMap[key] = value }

	fun translate(locale: Locale): String {
		return I18n.getString(token, placeholdersMap, locale)
	}
}