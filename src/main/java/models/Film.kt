package models

/*
    Model de Filme
    Atributos:
        id: int
        name: string
        theme: string
        available: string
        rating: float (0.00 - 10.00)
 */
data class Film(val id: Int? = null, val name: String, val theme: String, val available: String,
                val rating: Float)