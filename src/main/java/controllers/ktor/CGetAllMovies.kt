package controllers.ktor

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import main.java.interfaces.IRepo
import usecases.UCCRUDMovie

class CGetAllMovies  (movieDB: IRepo) {
    //Banco de dados
    val movieDB = movieDB
    //UseCase
    val useCase = UCCRUDMovie(movieDB)

    fun exec(): String{
        try{
            var movies = useCase.getAll()
            val mapper = jacksonObjectMapper()
            return mapper.writeValueAsString(movies)
        }
        catch(e: Exception){

        }
        return "Erro no Controller get all movies"
    }
}