package usecases

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import main.java.interfaces.IRepo
import models.Movie
import usecases.errors.ErrorsCRUDMovie
import usecases.errors.ErrorsCRUDReview

/*
    Classe responsável por armazenar os usecases de CRUD de Moviees
    Construtor:
        dataBase: IRepo -> Banco de dados de Moviees
 */
class UCCRUDMovie (dataBase: IRepo){

    // Banco de dados de Moviees
    val db = dataBase
    /*
        Adiciona ao banco de dados um Moviee a partir de um objeto Moviee
        Parâmetros:
            Movie: Movie
        Retorno:
            Boolean
     */
    fun createByObject(Movie: Movie){
            this.db.insertOne(Movie)
    }

    /*
        Adiciona ao banco de dados um Moviee a partir de um Map de Moviee
        Parâmetros:
            MovieJSON: String = """{"name": String,
                               "theme": String,
                               "available": String,
                               "rating": Float,
                               "poster": String}"""
        Retorno:
            Boolean
     */
    fun createByJson(MovieJSON: String){
        try{
            val mapper = jacksonObjectMapper()
            val Movie: Movie = mapper.readValue(MovieJSON)
            this.db.insertOne(Movie)
        }
        catch (e: Exception){
            throw ErrorsCRUDMovie("Erro ao tentar inserir Moviee com algum parâmetro nulo [usecase CRUDMovie.createByJson]")
        }
    }

    /*
        Retorna um Moviee pelo ID
        Parâmetro:
            id: Int
        Retorno:
            Movie
     */
    fun getByID(id: Int) : Any{
        if(id>=1){
            return this.db.getOne(id)
        }
        else{
            throw ErrorsCRUDMovie("Erro ao tentar adquirir file por id inválido [usecase CRUDMovie.getByID]")
        }
    }

    /*
        Atualiza as informações de um Moviee
        Parâmetros:
            id: Int -> ID do Moviee a ser atualizado
            newMovieData: Movie -> Novos dados do Moviee
        Retorno:
            Boolean
     */
    fun updateByID(id: Int, newMovieData: Movie){
        try{
            this.db.update(id, newMovieData)
        }
        catch (e: Exception){
            throw ErrorsCRUDMovie("Erro ao tentar atualizar dados do Moviee de ID: $id [usecase CRUDMovie.updateByID]")
        }
    }

    /*
        Deleta um Moviee a partir do id
        Parâmetros:
            id: Int -> ID do Moviee a ser deletado
        Retorno:
            Boolean
     */
    fun deleteByID(id: Int){
        try{
            this.db.delete(id)
        }
        catch (e: Exception){
            throw ErrorsCRUDMovie("Erro ao tentar deletar dados do Moviee de ID: $id [usecase CRUDMovie.deleteByID]")
        }
    }

    /*
        Retorna todos os filmes
     */
    fun getAll(name: String, theme: String, available: String) : List<Any>{
        try {
            return this.db.getAll(name, theme, available)
        }
        catch (e: Exception){
            throw ErrorsCRUDReview("Erro ao tentar adquirir reviews [usecase CRUDReview.getAll]")
        }
    }

}