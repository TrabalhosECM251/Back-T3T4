package interfaces

import models.Review

/*
    Interface de métodos que devem ser implementados por um controlador
 */

interface ICtrl {
    fun getOneReview(id: Int): String
    fun createReview(reviewJSON: String)
    fun getAllReviews(): String

    fun getOneMovie(id: Int): String
    fun createMovie(review: Review)
    fun getAllMovies(): String
}