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
                println("Iniciando criacao do review")
                useCase.createByJson(reviewJSON)
                println("Teoricamente criou o review")
            }
            catch(e: Exception){
            }
        }
    }
