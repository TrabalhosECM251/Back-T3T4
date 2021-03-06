package main.java.repositories.mariadb

import main.java.interfaces.IRepo
import models.User
import repositories.mariadb.MariaDB
import java.text.SimpleDateFormat
import java.util.*

/*
    Classe responsável por declarar métodos de manipulação do DB de User
 */

class UserDB : IRepo {
    override fun getOne(id: Int): Any {
        var user : User? = null

        try {
            val connection = MariaDB()
            println(SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time) + " LOG: Tentando fazer SELECT na tabela Users")
            val resultSet = connection.executeQuery("SELECT * FROM aplicacaoDB.Usuarios WHERE id = ${id};")

            while (resultSet!!.next()){
                user = User(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("usuario"),
                    resultSet.getString("senha")
                )
            }
            connection.close()
        }
        catch (exception:Exception){
            exception.printStackTrace()
        }
        return user!!
    }

    override fun getAll(objeto: Any?): List<Any> {
        val users = mutableListOf<User>()
        try {

            val connection = MariaDB()
            println(SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time) + " LOG: Tentando fazer SELECT na tabela Users")
            val resultSet = connection.executeQuery("SELECT * FROM aplicacaoDB.Usuarios;")

            while (resultSet!!.next()) {
                users.add(
                    User(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("usuario"),
                        resultSet.getString("senha")
                    )
                )
            }
            connection.close()
        }
        catch (exception:Exception){
            exception.printStackTrace()
        }

        return users
    }

    override fun insertOne(objeto: Any) : Boolean {
        val user : User = objeto as User

        try {
            val connection = MariaDB()
            println(SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time) + " LOG: Tentando fazer INSERT na tabela Users")
            val resultSet = connection.executeQuery("INSERT INTO aplicacaoDB.Usuarios (nome, usuario, senha) VALUES (\"${user.name}\", \"${user.user}\", \"${user.password}\");")
            connection.close()
        }
        catch (exception:Exception){
            exception.printStackTrace()
            return false
        }
        return true
    }

    override fun insertMult(lista: List<Any>) : Boolean {
        val users : List<User> = lista as List<User>

        try {
            val connection = MariaDB()
            println(SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time) + " LOG: Tentando fazer INSERT na tabela Users")
            for (user : User in users){
                val resultSet = connection.executeQuery("INSERT INTO aplicacaoDB.Usuarios (nome, usuario, senha) VALUES (\"${user.name}\", \"${user.user}\", \"${user.password}\");")
            }
            connection.close()
        }
        catch (exception:Exception){
            exception.printStackTrace()
            return false
        }
        return true
    }

    override fun update(id: Int, newObject: Any) : Boolean {
        val user : User = newObject as User
        try{
            val connection = MariaDB()
            println(SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time) + " LOG: Tentando fazer UPDATE na tabela Users")
            val resultSet = connection.executeQuery("UPDATE aplicacaoDB.Usuarios SET name = \"${user.name}\", email = \"${user.user}\", senha = \"${user.password}\" WHERE id = ${id};")
            connection.close()
        }
        catch (exception:Exception){
            exception.printStackTrace()
            return false
        }
        return true
    }

    override fun delete(id: Int) : Boolean{
        try{
            val connection = MariaDB()
            println(SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time) + " LOG: Tentando fazer DELETE na tabela Users")
            val resultSet = connection.executeQuery("DELETE FROM aplicacaoDB.Users WHERE id = ${id};")

            connection.close()
        }
        catch (exception:Exception){
            exception.printStackTrace()
            return false
        }
        return true
    }

    override fun getAllByIDMovie(id: Int): List<Any> {
        TODO("DOES NOT IMPLEMENT")
    }

    override fun existByUser(user: String): Any {
        var user : User = User(name = "null", user = "null", password = "null")

        try {
            val connection = MariaDB()
            println(SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time) + " LOG: Tentando fazer SELECT na tabela Users")
            val resultSet = connection.executeQuery("SELECT * FROM aplicacaoDB.Usuarios WHERE usuario = \"$user\";")

            while (resultSet!!.next()){
                user = User(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("usuario"),
                    resultSet.getString("senha")
                )
            }
            connection.close()
        }
        catch (exception:Exception){
            exception.printStackTrace()
        }
        return user!!
    }
}