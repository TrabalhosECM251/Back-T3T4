package main.java.repositories.mariadb

import main.java.interfaces.IRepo
import models.Review
import models.User
import models.enums.Classification
import repositories.mariadb.MariaDB

/*
    Classe responsável por declarar métodos de manipulação do DB de User
 */

class UserDB : IRepo {
    override fun getOne(id: Int): Any {
        var user : User? = null

        try {
            val connection = MariaDB();

            val resultSet = connection.executeQuery("SELECT * FROM aplicacaoDB.Users WHERE id = ${id};");

            while (resultSet!!.next()){
                user = User(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("email"),
                    resultSet.getString("senha")
                )
            }
            connection.close();
        }
        catch (exception:Exception){
            exception.printStackTrace()
        }
        return user!!
    }

    override fun getAll(): List<Any> {
        val reviews = mutableListOf<User>()
        try {

            val connection = MariaDB()
            val resultSet = connection.executeQuery("SELECT * FROM aplicacaoDB.Reviews;")

            while (resultSet!!.next()) {
                reviews.add(
                    User(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("email"),
                        resultSet.getString("senha")
                    )
                )
            }
            connection.close();
        }
        catch (exception:Exception){
            exception.printStackTrace()
        }

        return reviews;
    }

    override fun insertOne(objeto: Any) : Boolean {
        val user : User = objeto as User;

        try {
            val connection = MariaDB();
            val resultSet = connection.executeQuery("INSERT INTO aplicacaoDB.Users (nome, email, senha) VALUES ('${user.name}', '${user.email}', '${user.password}');")
            connection.close();
            return true
        }
        catch (exception:Exception){
            exception.printStackTrace()
            return false
        }
    }

    override fun insertMult(lista: List<Any>) : Boolean {
        val users = lista as List<User>

        try {
            val connection = MariaDB();
            for (user : User in users){
                val resultSet = connection.executeQuery("INSERT INTO aplicacaoDB.Users (nome, email, senha) VALUES ('${user.name}', '${user.email}', '${user.password}');")
            }
            connection.close();
            return true
        }
        catch (exception:Exception){
            exception.printStackTrace()
            return false
        }
    }

    override fun update(id: Int, newObject: Any) : Boolean {
        val user : User = newObject as User
        try{
            val connection = MariaDB()

            val resultSet = connection.executeQuery("UPDATE aplicacaoDB.Users SET name = '${user.name}', email = '${user.email}', senha = '${user.password}' WHERE id = ${id};")
            connection.close()
            return true
        }
        catch (exception:Exception){
            exception.printStackTrace()
            return false
        }
    }

    override fun delete(id: Int) : Boolean{
        try{
            val connection = MariaDB()

            val resultSet = connection.executeQuery("DELETE FROM aplicacaoDB.Users WHERE id = ${id};")

            connection.close()
            return true
        }
        catch (exception:Exception){
            exception.printStackTrace()
            return false
        }
    }
}