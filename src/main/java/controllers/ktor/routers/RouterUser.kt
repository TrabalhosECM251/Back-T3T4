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
        post("/e"){
            val formParameters = call.receiveParameters()
            val email = formParameters["e"].toString()
            val pass = formParameters["p"].toString()
            val exist = KtorControllerFabric(userDB).existByEmailAndPass(email, pass)
            if(exist == "false"){
                call.respondText(exist, status = HttpStatusCode.NotFound)
            }
            else {
                call.respondText(exist, status = HttpStatusCode.OK)
            }
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