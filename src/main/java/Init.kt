import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import config.Ktor


/*
    Classe responsável pela inicialização do servidor
*/

class Init {
    fun start() {
        embeddedServer(Netty, port = Ktor.port, host = Ktor.host) {
            routing {
                get("/") {
                    call.respondText("Vambora")
                }
            }
        }.start(wait = true)
    }
}