package main.java.controllers.ktor.routers

import fabrics.controllers.ktor.KtorControllerFabric
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import main.java.interfaces.IRepo
import models.Review

/*
	Classe responsável pelo manejamento de rotas
 */

fun Route.reviewRoutes(reviewDB: IRepo){

    route("/reviews") {

        //Rota que retorna todas as reviews
        get{
            return@get call.respondText(KtorControllerFabric(reviewDB).getAllReviews(), status = HttpStatusCode.OK)
        }

        //Rota que retorna um review
        get("{id}"){
            val id = call.parameters["id"]!!.toInt()
            return@get call.respondText(KtorControllerFabric(reviewDB).getOneReview(id), status = HttpStatusCode.OK)
        }

        //Rota que posta uma review
        post {
            val review = call.receive<String>()
            println("TEXTO REVIEW "+ review)
            KtorControllerFabric(reviewDB).createReview(review)
            call.respondText("Review Criada", status = HttpStatusCode.Created)
        }
    }
}

fun Application.registerReviewRoutes(reviewDB: IRepo){
    routing {
        reviewRoutes(reviewDB)
    }
}