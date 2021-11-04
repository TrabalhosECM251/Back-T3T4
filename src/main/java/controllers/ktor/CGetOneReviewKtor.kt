package controllers.ktor

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import main.java.interfaces.IRepo
import usecases.UCCRUDReview

class CGetOneReviewKtor(repository: IRepo ) {


    //Banco de dados
    val repository = repository
    //UseCase
    val useCase = UCCRUDReview(repository)

    fun exec(id: Int): String{
        try{
            var review = useCase.getByID(id)
            val mapper = jacksonObjectMapper()
            return mapper.writeValueAsString(review)
        }
        catch(e: Exception){

        }
        return "Erro no Controller get one review"
    }
}