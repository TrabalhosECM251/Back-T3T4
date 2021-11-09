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

        //Rota que retorna todos os usuarios
        get{
        }

        //Rota que retorna um usuario pelo seu id
        get("{id}"){
        }

        //Rota que retorna se um usuario existe
        get("/e"){
            val email = call.request.queryParameters["e"]!!
            val pass = call.request.queryParameters["p"]!!
            val exist = KtorControllerFabric(userDB).existByEmailAndPass(email, pass)
            if(exist == "false"){
                return@get call.respondText(exist, status = HttpStatusCode.NotFound)
            }
            return@get call.respondText(exist, status = HttpStatusCode.OK)
        }

        //Rota que cria um usuario no banco
        post {
        }
    }
}

fun Application.registerUserRoutes(userDB: IRepo){
    routing {
        userRoutes(userDB)
    }
}