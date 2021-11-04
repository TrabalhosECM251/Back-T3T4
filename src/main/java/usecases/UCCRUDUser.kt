package usecases

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import main.java.interfaces.IRepo
import models.User
import usecases.errors.ErrorsCRUDUser
/*
    Classe responsável por armazenar os usecases de CRUD de Usuários
    Construtor:
        dataBase: IRepo -> Banco de dados de Usuários
 */
class UCCRUDUser (dataBase: IRepo){

    // Banco de dados de Usuários
    val db = dataBase
    /*
        Adiciona ao banco de dados um usuário a partir de um objeto usuário
        Parâmetros:
            User: User
        Retorno:
            Boolean
     */
    fun createByObject(User: User){
        this.db.insertOne(User)
    }

    /*
        Adiciona ao banco de dados um usuário a partir de um Map de usuário
        Parâmetros:
            userJSON: String = """{"name": String,
                               "email": String,
                               "password": String}"""
        Retorno:
            Boolean
     */
    fun createByJson(userJSON: String){
        try{
            val mapper = jacksonObjectMapper()
            val User: User = mapper.readValue(userJSON)
            this.db.insertOne(User)
        }
        catch (e: Exception){
            throw ErrorsCRUDUser("Erro ao tentar inserir usuário com algum parâmetro nulo [usecase CRUDUser.createByJson]")
        }
    }

    /*
        Retorna um usuário pelo ID
        Parâmetro:
            id: Int
        Retorno:
            User
     */
    fun getByID(id: Int) : Any{
        if(id>=1){
            return this.db.getOne(id)
        }
        else{
            throw ErrorsCRUDUser("Erro ao tentar adquirir file por id inválido [usecase CRUDUser.getByID]")
        }
    }

    /*
        Atualiza as informações de um usuário
        Parâmetros:
            id: Int -> ID do usuário a ser atualizado
            newUserData: User -> Novos dados do usuário
        Retorno:
            Boolean
     */
    fun updateByID(id: Int, newUserData: User){
        try{
            this.db.update(id, newUserData)
        }
        catch (e: Exception){
            throw ErrorsCRUDUser("Erro ao tentar atualizar dados do usuário de ID: $id [usecase CRUDUser.updateByID]")
        }
    }

    /*
        Deleta um usuário a partir do id
        Parâmetros:
            id: Int -> ID do usuário a ser deletado
        Retorno:
            Boolean
     */
    fun deleteByID(id: Int){
        try{
            this.db.delete(id)
        }
        catch (e: Exception){
            throw ErrorsCRUDUser("Erro ao tentar deletar dados do usuário de ID: $id [usecase CRUDUser.deleteByID]")
        }
    }

}