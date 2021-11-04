package usecases

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import main.java.interfaces.IRepo
import models.Film
import usecases.errors.ErrorsCRUDFilm
/*
    Classe responsável por armazenar os usecases de CRUD de Filmes
    Construtor:
        dataBase: IRepo -> Banco de dados de filmes
 */
class UCCRUDFilm (dataBase: IRepo){

    // Banco de dados de filmes
    val db = dataBase
    /*
        Adiciona ao banco de dados um filme a partir de um objeto Filme
        Parâmetros:
            film: Film
        Retorno:
            Boolean
     */
    fun createByObject(film: Film){
            this.db.insertOne(film)
    }

    /*
        Adiciona ao banco de dados um filme a partir de um Map de Filme
        Parâmetros:
            filmJSON: String = """{"name": String,
                               "theme": String,
                               "available": String,
                               "rating": Float,
                               "poster": String}"""
        Retorno:
            Boolean
     */
    fun createByJson(filmJSON: String){
        try{
            val mapper = jacksonObjectMapper()
            val film: Film = mapper.readValue(filmJSON)
            this.db.insertOne(film)
        }
        catch (e: Exception){
            throw ErrorsCRUDFilm("Erro ao tentar inserir filme com algum parâmetro nulo [usecase CRUDFilm.createByJson]")
        }
    }

    /*
        Retorna um filme pelo ID
        Parâmetro:
            id: Int
        Retorno:
            Film
     */
    fun getByID(id: Int) : Any{
        if(id>=1){
            return this.db.getOne(id)
        }
        else{
            throw ErrorsCRUDFilm("Erro ao tentar adquirir file por id inválido [usecase CRUDFilm.getByID]")
        }
    }

    /*
        Atualiza as informações de um filme
        Parâmetros:
            id: Int -> ID do filme a ser atualizado
            newFilmData: Film -> Novos dados do filme
        Retorno:
            Boolean
     */
    fun updateByID(id: Int, newFilmData: Film){
        try{
            this.db.update(id, newFilmData)
        }
        catch (e: Exception){
            throw ErrorsCRUDFilm("Erro ao tentar atualizar dados do filme de ID: $id [usecase CRUDFilm.updateByID]")
        }
    }

    /*
        Deleta um filme a partir do id
        Parâmetros:
            id: Int -> ID do filme a ser deletado
        Retorno:
            Boolean
     */
    fun deleteByID(id: Int){
        try{
            this.db.delete(id)
        }
        catch (e: Exception){
            throw ErrorsCRUDFilm("Erro ao tentar deletar dados do filme de ID: $id [usecase CRUDFilm.deleteByID]")
        }
    }

}