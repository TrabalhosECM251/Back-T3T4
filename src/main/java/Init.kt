import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import config.Ktor
import controllers.ktor.routers.configureRouting
import main.java.repositories.mariadb.FilmDB // Apagar dps
import models.Film
import main.java.repositories.mariadb.ReviewDB
import main.java.repositories.mariadb.UserDB


/*
    Classe responsável pela inicialização do servidor
*/

class Init {
    private val userDB = UserDB()
    private val filmDB = FilmDB()
    private val reviewDB = ReviewDB()

    fun start() {
        embeddedServer(Netty, port = Ktor.port, host = Ktor.host) {
            routing {
                configureRouting(userDB, reviewDB, filmDB)
            }
        }.start(wait = true)
    }
}