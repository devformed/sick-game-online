package com.devformed.sickgame.exception

/**
 * @author Anton Gorokh
 */
class Rule<T>(val errorKey: String, val condition: (T) -> Boolean)