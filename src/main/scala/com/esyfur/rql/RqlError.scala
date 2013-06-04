package com.esyfur.rql

class RqlError(message: String) extends RuntimeException {

}

class RqlClientError(message: String) extends RqlError(message) {

}

class RqlCompileError(message: String) extends RqlError(message) {

}

class RqlRuntimeError(message: String) extends RqlError(message) {

}

class RqlDriverError(message: String) extends RqlError(message) {

}
