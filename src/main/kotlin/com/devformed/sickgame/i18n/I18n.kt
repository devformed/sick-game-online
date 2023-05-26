package com.devformed.sickgame.i18n

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.FileReader
import java.io.IOException
import java.util.Locale
import java.util.PropertyResourceBundle
import java.util.ResourceBundle

/**
 * @author Anton Gorokh
 */
object I18n {

	private const val bundlePathProperty: String = "translations.bundle.path"
	private const val bundleBasePath: String = "i18n/translations"
	private val log: Logger = LoggerFactory.getLogger(this::class.java)
	private val supportedLocales: List<String> = listOf("en", "pl", "ru", "ua")
	private val bundleByLocale: Map<Locale, ResourceBundle>

	init {
		val map = mutableMapOf<Locale, ResourceBundle>()
		supportedLocales.forEach { map[Locale.forLanguageTag(it)] = getResourceBundle(it) }
		bundleByLocale = map.toMap()
	}

	fun getString(key: String, locale: Locale) = getString(key, mapOf(), locale)

	fun getString(key: String, placeholdersMap: Map<String, String>, locale: Locale): String {
		val bundle: ResourceBundle? = bundleByLocale[locale]
		return if (bundle != null && bundle.containsKey(key)) {
			bundle.getString(key)
		} else {
			log.warn("Failed to obtain translation for key $key")
			"!$key!"
		}
	}

	private fun getResourceBundle(locale: String): ResourceBundle {
		val bundlePath: String? = System.getProperty(bundlePathProperty)
		return bundlePath?.let { toFileBundle(it) } ?: ResourceBundle.getBundle(bundleBasePath, Locale.forLanguageTag(locale))
	}

	private fun toFileBundle(locale: String): ResourceBundle? {
		return try {
			FileReader(toFilePath(locale)).use { PropertyResourceBundle(it) }
		} catch (e: IOException) {
			log.error("Failed to load bundle for locale $locale", e)
			null
		}
	}

	private fun toFilePath(locale: String): String =
		"$bundleBasePath/translations_$locale.xml"
}