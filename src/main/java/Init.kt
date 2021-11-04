import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import config.Ktor
import main.java.repositories.mariadb.FilmDB // Apagar dps
import models.Film


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

                get("/delete/{query}"){
                    val id = call.parameters["query"]!!.toInt();
                    val filmDB = FilmDB()
                    val deletedFilm = filmDB.getOne(id) as Film
                    filmDB.delete(id)
                    call.respondText { "Filme deletado: ${deletedFilm.name}" }
                }

                get("/insert/"){
                    val filmDB = FilmDB()

                    val film1 = Film(0,"Quatro vidas de um cachorro", "Cachorro", "YouTube", 9.0.toFloat(), "" )
                    val film2 = Film( 0,"Meu amigo Enzo", "Cachorro", "Now", 9.0.toFloat(), "" )
                    val film3 = Film( 0,"Intocaveis", "Drama", "Netflix", 9.4.toFloat(), "" )

                    val films = mutableListOf<Film>()

                    films.add(film1)
                    films.add(film2)
                    films.add(film3)

                    filmDB.insertMult(films)

                    call.respondText { "Inseridos" }
                }

                get("/insertOne/"){
                    val filmDB = FilmDB()

                    filmDB.insertOne(Film(0,"Quatro vidas de um cachorro", "Cachorro", "YouTube", 9.0.toFloat(), "" ))

                    call.respondText { "Inserido" }
                }

                get("/update/{query}"){
                    val filmDB = FilmDB()
                    val film1 = Film(0,"Quatro vidas de um cachorro", "Cachorro", "YouTube", 6.7.toFloat(), "" )
                    val id = call.parameters["query"]!!.toInt();

                    filmDB.update(id, film1)

                    call.respondText { "Updatado" }
                }

                get("/getAll/"){
                    val filmDB = FilmDB()
                    val films = filmDB.getAll() as List<Film>
                    var stringResp = ""

                    for(film : Film in films){
                        stringResp = stringResp + "Filme: ${film.name} - Disponivel: ${film.available} \n"
                    }
                    call.respondText {stringResp}
                }

            }
        }.start(wait = true)
    }
}