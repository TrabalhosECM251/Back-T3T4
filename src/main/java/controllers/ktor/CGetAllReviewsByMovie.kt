package controllers.ktor

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import main.java.interfaces.IRepo
import main.java.repositories.mariadb.ReviewDB
import usecases.UCCRUDReview

class CGetAllReviewsByMovie (reviewDB: IRepo){
    //Banco de dados
    val reviewDB = reviewDB
    //UseCase
    val useCase = UCCRUDReview(reviewDB)

    fun exec(id: Int): String{
        try{
            var reviews = useCase.getAllByIDMovie(id)
            val mapper = jacksonObjectMapper()
            return mapper.writeValueAsString(reviews)
        }
        catch(e: Exception){

        }
        return "Erro no Controller getAllByIDMovie"
    }
}