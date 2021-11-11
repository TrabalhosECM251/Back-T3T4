package controllers.ktor

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import main.java.interfaces.IRepo
import usecases.UCCRUDMovie

class CGetOneMovie (movieDB: IRepo) {


    //Banco de dados
    val movieDB = movieDB
    //UseCase
    val useCase = UCCRUDMovie(movieDB)

    fun exec(id: Int): String{
        try{
            var movie = useCase.getByID(id)
            val mapper = jacksonObjectMapper()
            val results = mapper.writeValueAsString(movie)
            return """{"results": $results}"""
        }
        catch(e: Exception){

        }
        return "Erro no Controller get one movie"
    }
}