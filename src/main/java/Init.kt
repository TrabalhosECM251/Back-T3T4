import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import config.Ktor
import controllers.ktor.routers.configureRouting
import main.java.repositories.mariadb.MovieDB
import main.java.repositories.mariadb.ReviewDB
import main.java.repositories.mariadb.UserDB
import io.ktor.features.*
import io.ktor.http.*


/*
    Classe responsável pela inicialização do servidor
*/

class Init {
    private val userDB = UserDB()
    private val MovieDB = MovieDB()
    private val reviewDB = ReviewDB()

    fun start() {
        embeddedServer(Netty, port = Ktor.port, host = Ktor.host) {
            routing {
                configureRouting(userDB, reviewDB, MovieDB)
            }
            install(CORS) {
                method(HttpMethod.Options)
                anyHost()
            }
        }.start(wait = true)
    }
}