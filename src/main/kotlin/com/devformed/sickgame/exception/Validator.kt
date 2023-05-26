package com.devformed.sickgame.exception

import com.devformed.sickgame.i18n.I18nToken
import java.util.Locale

/**
 * @author Anton Gorokh
 */
class Validator<T>(private val rules: MutableCollection<Rule<T>>) {

	private val failedRules = mutableListOf<Rule<T>>()

	constructor() : this(mutableListOf())

	fun addRule(rule: Rule<T>): Validator<T> {
		return this.apply { rules.add(rule) }
	}

	fun validate(obj: T?): Boolean {
		failedRules.clear()
		return obj != null && rules.all { rule ->
			rule.condition(obj).also { if (!it) failedRules.add(rule) }
		}
	}

	fun throwIfInvalid(obj: T?, locale: Locale) {
		if (validate(obj).not()) {
			throw ValidationException(failedRules.map { I18nToken(it.errorKey) }, locale)
		}
	}
}