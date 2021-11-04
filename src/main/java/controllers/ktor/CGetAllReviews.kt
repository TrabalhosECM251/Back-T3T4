package controllers.ktor

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import main.java.interfaces.IRepo
import usecases.UCCRUDReview

class CGetAllReviews (repository: IRepo){
    //Banco de dados
    val repository = repository
    //UseCase
    val useCase = UCCRUDReview(repository)

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