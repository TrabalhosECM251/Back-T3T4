package models

/*
    Model de usuário
    Atributos:
        name: string - nome do usuário
        email: string - email do usuário
        password: string - senha do usuário
 */

data class User(val id: Int? = null, val name: String, val user: String, val password: String)