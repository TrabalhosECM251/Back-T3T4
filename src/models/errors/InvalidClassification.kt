package models.errors

import java.lang.Exception

class InvalidClassification: Exception {
    constructor() : super("Classificação inválida!")
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}