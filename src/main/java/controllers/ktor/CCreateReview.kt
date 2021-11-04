package controllers.ktor


import main.java.interfaces.IRepo
import models.Review
import usecases.UCCRUDReview

class CCreateReview (repository: IRepo){
        //Banco de dados
        val repository = repository
        //UseCase
        val useCase = UCCRUDReview(repository)

        fun exec(review: Review){
            try{
                useCase.createByObject(review)
            }
            catch(e: Exception){
            }
        }
    }
