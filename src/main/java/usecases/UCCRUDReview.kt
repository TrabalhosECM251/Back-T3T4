package usecases

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import main.java.interfaces.IRepo
import models.Review
import usecases.errors.ErrorsCRUDReview
/*
    Classe responsável por armazenar os usecases de CRUD de reviews
    Construtor:
        dataBase: IRepo -> Banco de dados de reviews
 */
class UCCRUDReview (dataBase: IRepo){

    // Banco de dados de reviews
    val db = dataBase
    /*
        Adiciona ao banco de dados um review a partir de um objeto review
        Parâmetros:
            Review: Review
        Retorno:
            Boolean
     */
    fun createByObject(Review: Review){
        this.db.insertOne(Review)
    }

    /*
        Adiciona ao banco de dados um review a partir de um Map de review
        Parâmetros:
            reviewJSON: String = """{"idFilm": Int,
                                     "idUser": Int,
                                     "classification": Classification,
                                     "rating": Float,
                                     "comment": String}"""
        Retorno:
            Boolean
     */
    fun createByJson(reviewJSON: String){
        try{
            val mapper = jacksonObjectMapper()
            val review: Review = mapper.readValue(reviewJSON)
            this.db.insertOne(review)
        }
        catch (e: Exception){
            throw ErrorsCRUDReview("Erro ao tentar inserir review com algum parâmetro nulo [usecase CRUDReview.createByJson]")
        }
    }

    /*
        Retorna um review pelo ID
        Parâmetro:
            id: Int
        Retorno:
            Review
     */
    fun getByID(id: Int) : Any{
        if(id>=1){
            return this.db.getOne(id)
        }
        else{
            throw ErrorsCRUDReview("Erro ao tentar adquirir file por id inválido [usecase CRUDReview.getByID]")
        }
    }

    /*
        Atualiza as informações de um review
        Parâmetros:
            id: Int -> ID do review a ser atualizado
            newReviewData: Review -> Novos dados do review
        Retorno:
            Boolean
     */
    fun updateByID(id: Int, newReviewData: Review){
        try{
            this.db.update(id, newReviewData)
        }
        catch (e: Exception){
            throw ErrorsCRUDReview("Erro ao tentar atualizar dados do review de ID: $id [usecase CRUDReview.updateByID]")
        }
    }

    /*
        Deleta um review a partir do id
        Parâmetros:
            id: Int -> ID do review a ser deletado
        Retorno:
            Boolean
     */
    fun deleteByID(id: Int){
        try{
            this.db.delete(id)
        }
        catch (e: Exception){
            throw ErrorsCRUDReview("Erro ao tentar deletar dados do review de ID: $id [usecase CRUDReview.deleteByID]")
        }
    }

}