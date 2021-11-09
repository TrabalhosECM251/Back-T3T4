package usecases.errors

import java.lang.Exception

class ErrorsCRUDReview : Exception {
    constructor() : super("Erro em operação envolvendo Moviee")
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}