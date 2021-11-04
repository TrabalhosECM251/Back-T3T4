package fabrics.controllers.ktor

import controllers.ktor.CGetOneReviewKtor
import controllers.ktor.CCreateReview
import controllers.ktor.CGetAllReviews
import interfaces.ICtrl
import main.java.interfaces.IRepo
import models.Review

class KtorControllerFabric (repository: IRepo) : ICtrl{
    val repository = repository

    override fun getOneReview(id: Int): String{
        return CGetOneReviewKtor(repository).exec(id)
    }

    override fun createReview(review : Review) {
        return CCreateReview(repository).exec(review)
    }

    override fun getAllReviews(): String {
        return CGetAllReviews(repository).exec()
    }

    override fun getOneMovie(id: Int): String {
        TODO("Not yet implemented")
    }

    override fun createMovie(review: Review) {
        TODO("Not yet implemented")
    }

    override fun getAllMovies(): String {
        TODO("Not yet implemented")
    }
}