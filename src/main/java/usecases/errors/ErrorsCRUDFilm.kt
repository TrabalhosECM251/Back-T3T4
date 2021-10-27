package usecases.errors

import java.lang.Exception

class ErrorsCRUDFilm : Exception {
    constructor() : super("Erro em operação envolvendo filme")
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}