package controllers.ktor


import main.java.interfaces.IRepo
import models.Review
import usecases.UCCRUDReview

class CCreateReview (reviewDB: IRepo){
        //Banco de dados
        val reviewDB = reviewDB
        //UseCase
        val useCase = UCCRUDReview(reviewDB)

        fun exec(reviewJSON: String){
            try{
                useCase.createByJson(reviewJSON)
            }
            catch(e: Exception){
            }
        }
    }
