package controllers.ktor.routers

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import main.java.interfaces.IRepo
import main.java.controllers.ktor.routers.registerReviewRoutes

fun Application.configureRouting(userDB: IRepo, reviewDB: IRepo, MovieDB: IRepo){
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        registerReviewRoutes(reviewDB)
        registerUserRoutes(userDB)
        registerMovieRoutes(MovieDB)
    }
}