package controllers.ktor.routers

import fabrics.controllers.ktor.KtorControllerFabric
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import main.java.interfaces.IRepo

/*
	Classe responsável pelo manejamento de rotas
 */

fun Route.userRoutes(userDB: IRepo){

    route("/user") {

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

fun Application.registerUserRoutes(userDB: IRepo){
    routing {
        userRoutes(userDB)
    }
}