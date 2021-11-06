package fabrics.controllers.ktor

import controllers.ktor.*
import interfaces.ICtrl
import main.java.interfaces.IRepo


class KtorControllerFabric (repository: IRepo) : ICtrl{
    val repository = repository

    override fun getOneReview(id: Int): String{
        return CGetOneReview(repository).exec(id)
    }

    override fun createReview(reviewJSON : String) {
        return CCreateReview(repository).exec(reviewJSON)
    }

    override fun getAllReviews(): String {
        return CGetAllReviews(repository).exec()
    }

    override fun getOneMovie(id: Int): String {
        return CGetOneMovie(repository).exec(id)
    }

    override fun createMovie(movieJSON: String) {
        TODO("Not yet implemented")
    }

    override fun getAllMovies(filterJSON: String): String {
        return CGetAllMovies(repository).exec(filterJSON)
    }
}