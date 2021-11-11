package controllers.ktor

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import main.java.interfaces.IRepo
import usecases.UCCRUDUser

class CUserExistByEmail(userDB: IRepo) {


    //Banco de dados
    val userDB = userDB
    //UseCase
    val useCase = UCCRUDUser(userDB)

    fun exec(email: String, pass: String): String{
        try{
            val exists = useCase.existByEmailAndPass(email, pass)
            val mapper = jacksonObjectMapper()
            val results = mapper.writeValueAsString(exists)
            return """{"results": $results}"""
        }
        catch(e: Exception){

        }
        return "Erro no Controller CUserExistByEmail"
    }
}