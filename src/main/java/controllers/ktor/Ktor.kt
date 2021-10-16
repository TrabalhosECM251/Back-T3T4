package controllers.ktor

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

object Ktor {
    val protocol = "http"
    val host = "localhost"
    val port = 8080

    fun start() {
        println("OI")
        embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
            routing {
                get("/") {
                    call.respondText("Vambora")
                }
            }
        }.start(wait = true)
    }

}



