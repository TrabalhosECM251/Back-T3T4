package controllers.ktor.routers

import io.ktor.application.*
import io.ktor.routing.*
import main.java.interfaces.IRepo

/*
	Classe responsável pelo manejamento de rotas
 */

fun Route.filmRoutes(filmDB: IRepo){

    route("/film") {

        //Rota que retorna todas as reviews
        get{
        }

        //Rota que retorna um review
        get("{id}"){
        }

        //Rota que posta uma review
        post {
        }
    }
}

fun Application.registerFilmRoutes(filmDB: IRepo){
    routing {
        filmRoutes(filmDB)
    }
}