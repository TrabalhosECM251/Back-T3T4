package models

/*
    Model de Movie
    Atributos:
        id: int
        name: string
        theme: string
        available: string
        rating: float (0.00 - 10.00)
        poster: string
 */
data class Movie(
    val id: Int? = null, val name: String, val theme: String, val available: String,
                val rating: Float, val poster: String)