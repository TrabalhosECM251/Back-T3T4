package controllers.ktor

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import main.java.interfaces.IRepo
import usecases.UCCRUDReview

class CGetOneReview(reviewDB: IRepo ) {


    //Banco de dados
    val reviewDB = reviewDB
    //UseCase
    val useCase = UCCRUDReview(reviewDB)

    fun exec(id: Int): String{
        try{
            var review = useCase.getByID(id)
            val mapper = jacksonObjectMapper()
            val results = mapper.writeValueAsString(review)
            return """{"results": $results}"""
        }
        catch(e: Exception){

        }
        return "Erro no Controller get one review"
    }
}