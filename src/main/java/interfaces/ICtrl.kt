package interfaces

import models.Filter
import models.Movie
import models.Review

/*
    Interface de métodos que devem ser implementados por um controlador
 */

interface ICtrl {
    fun getOneReview(id: Int): String
    fun createReview(reviewJSON: String)
    fun getAllReviews(): String

    fun getOneMovie(id: Int): String
    fun createMovie(movieJSON: String)
    fun getAllMovies(filterJSON: String): String

}