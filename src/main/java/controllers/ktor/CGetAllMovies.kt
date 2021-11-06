package controllers.ktor

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import main.java.interfaces.IRepo
import usecases.UCCRUDMovie

class CGetAllMovies  (movieDB: IRepo) {
    //Banco de dados
    val movieDB = movieDB
    //UseCase
    val useCase = UCCRUDMovie(movieDB)

    fun exec(filterJSON: String): String{
        try{
            /* TODO: RENAN AQUI TEM Q PASSAR OS FILTROS PRO GETALL
                SO QUE TEM Q PEGAR DE UM JSON N?, ENFIM TEMO Q PENSA SOBRE AQUI
             */
            var movies = useCase.getAll(filterJSON)
            val mapper = jacksonObjectMapper()
            return mapper.writeValueAsString(movies)
        }
        catch(e: Exception){

        }
        return "Erro no Controller get all movies"
    }
}