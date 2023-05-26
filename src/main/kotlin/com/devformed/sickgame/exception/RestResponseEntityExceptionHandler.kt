package com.devformed.sickgame.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

/**
 * @author Anton Gorokh
 */
@Component
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

	@ExceptionHandler(ValidationException::class)
	protected fun handleValidationException(ex: ValidationException, request: WebRequest): ResponseEntity<Any>? {
		val translatedErrors = sequenceOf(*ex.errors.toTypedArray())
			.map { it.translate(ex.locale) }
			.toList<String>()
		return ResponseEntity<Any>(translatedErrors.joinToString(", "), HttpHeaders(), HttpStatus.BAD_REQUEST)
	}
}