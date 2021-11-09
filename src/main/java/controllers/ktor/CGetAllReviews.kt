package controllers.ktor

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import main.java.interfaces.IRepo
import usecases.UCCRUDReview

class CGetAllReviews (reviewDB: IRepo){
    //Banco de dados
    val reviewDB = reviewDB
    //UseCase
    val useCase = UCCRUDReview(reviewDB)

    fun exec(): String{
        try{
            var reviews = useCase.getAll()
            val mapper = jacksonObjectMapper()
            return mapper.writeValueAsString(reviews)
        }
        catch(e: Exception){

        }
        return "Erro no Controller get one review"
    }
}