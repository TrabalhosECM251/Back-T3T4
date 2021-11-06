package controllers.ktor.routers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import fabrics.controllers.ktor.KtorControllerFabric
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import main.java.interfaces.IRepo
import models.Filter


/*
	Classe responsável pelo manejamento de rotas
 */

fun Route.MovieRoutes(movieDB: IRepo){

    route("/movies") {

        //Rota que retorna todas os movies
        get{
            val name = call.request.queryParameters["name"]!!
            val theme = call.request.queryParameters["theme"]!!
            val available = call.request.queryParameters["available"]!!

            val filter = Filter(name,theme,available)

            val mapper = jacksonObjectMapper()
            val filterJSON = mapper.writeValueAsString(filter)

            return@get call.respondText(KtorControllerFabric(movieDB).getAllMovies(filterJSON))
            //return@get call.respondText(KtorControllerFabric(movieDB).getAllMovies())
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