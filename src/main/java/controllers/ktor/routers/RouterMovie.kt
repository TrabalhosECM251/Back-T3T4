package controllers.ktor.routers

import fabrics.controllers.ktor.KtorControllerFabric
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import main.java.interfaces.IRepo

/*
	Classe responsável pelo manejamento de rotas
 */

fun Route.MovieRoutes(movieDB: IRepo){

    route("/movies") {

        //Rota que retorna todas os movies
        //TODO:  COMO Q A GNT PEGAR UMA FILTER DO USUARIO
        get{
            //return@get call.respondText(KtorControllerFabric(movieDB).getAllMovies(filter))
            return@get call.respondText(KtorControllerFabric(movieDB).getAllMovies())
        }

        //Rota que retorna um review
        get("{id}"){
            val id = call.parameters["id"]!!.toInt()
            return@get call.respondText(KtorControllerFabric(movieDB).getOneMovie(id))
        }

        //Rota que posta uma review
        post {
        }
    }
}

fun Application.registerMovieRoutes(movieDB: IRepo){
    routing {
        MovieRoutes(movieDB)
    }
}