package interfaces

import models.Review

/*
    Interface de métodos que devem ser implementados por um controlador
 */

interface ICtrl {
    fun getOneReview(id: Int): String
    fun createReview(review: Review)
    fun getAllReviews(): String
}