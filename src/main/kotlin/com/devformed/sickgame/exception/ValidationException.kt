package com.devformed.sickgame.exception

import com.devformed.sickgame.i18n.I18nToken
import com.devformed.sickgame.util.Constants
import java.util.Locale

/**
 * @author Anton Gorokh
 */
class ValidationException(val errors: Collection<I18nToken>, val locale: Locale) : RuntimeException() {

	constructor(errors: Collection<I18nToken>) : this(errors, Constants.defaultLocale)
}

