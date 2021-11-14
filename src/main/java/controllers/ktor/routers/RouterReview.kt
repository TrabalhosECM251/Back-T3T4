package main.java.controllers.ktor.routers

import fabrics.controllers.ktor.KtorControllerFabric
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import main.java.interfaces.IRepo
import models.Review
import java.util.*

/*
	Classe responsável pelo manejamento de rotas
 */

fun Route.reviewRoutes(reviewDB: IRepo){

    route("/reviews") {

        //Rota que retorna todas as reviews
        get{
            return@get call.respondText(KtorControllerFabric(reviewDB).getAllReviews(), status = HttpStatusCode.OK)
        }

        //Rota que retorna os reviews de um filme
        get("{idFilme}"){
            val idFilme = call.parameters["idFilme"]!!.toInt()
            return@get call.respondText(KtorControllerFabric(reviewDB).getAllReviewsByIDMovie(idFilme), status = HttpStatusCode.OK)
        }

        //Rota que posta uma review
        post {

            val formParameters = call.receiveParameters()
            val idMovie = formParameters["idmovie"]!!.toInt()
            val nmUser = formParameters["nmuser"]!!.toString()
            val rating = formParameters["rating"]!!.toFloat()
            val comment = formParameters["comment"].toString()

            val reviewJSON = """{"idMovie":$idMovie,
                                |"nmUser":$nmUser,
                                |"rating":$rating,
                                |"comment":"$comment"}""".trimMargin()
            KtorControllerFabric(reviewDB).createReview(reviewJSON)
            call.respondText("""{"results": "Review Criada"}""", status = HttpStatusCode.Created)
        }
    }
}

fun Application.registerReviewRoutes(reviewDB: IRepo){
    routing {
        reviewRoutes(reviewDB)
    }
}