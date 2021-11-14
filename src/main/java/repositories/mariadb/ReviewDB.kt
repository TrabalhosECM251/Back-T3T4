package main.java.repositories.mariadb

import main.java.interfaces.IRepo
import models.Review
import repositories.mariadb.MariaDB
import java.text.SimpleDateFormat
import java.util.*

/*
    Classe responsável por declarar métodos de manipulação do DB de Reviews
 */

class ReviewDB : IRepo {
    override fun getOne(id: Int): Any {
        var review : Review? = null

        try {
            val connection = MariaDB();
            println(SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time) + " LOG: Tentando fazer SELECT na tabela Reviews")
            val resultSet = connection.executeQuery("SELECT * FROM aplicacaoDB.Reviews WHERE id = ${id};");

            while (resultSet!!.next()){
                review = Review(
                    resultSet.getInt("id"),
                    resultSet.getInt("idFilme"),
                    resultSet.getString("nmUsuario"),
                    resultSet.getFloat("notaUsuario"),
                    resultSet.getString("comentario")
                )
            }
            connection.close();
        }
        catch (exception:Exception){
            exception.printStackTrace()
        }
        return review!!
    }

    override fun getAll(objeto: Any?): List<Any> {
        val reviews = mutableListOf<Review>()
        try {
            val connection = MariaDB()
            println(SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time) + " LOG: Tentando fazer SELECT na tabela Reviews")
            val resultSet = connection.executeQuery("SELECT * FROM aplicacaoDB.Reviews;")

            while (resultSet!!.next()) {
                reviews.add(
                    Review(
                        resultSet.getInt("id"),
                        resultSet.getInt("idFilme"),
                        resultSet.getString("nmUsuario"),
                        resultSet.getFloat("notaUsuario"),
                        resultSet.getString("comentario")
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
        val review : Review = objeto as Review;

        try {
            val connection = MariaDB();
            println(SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time) + " LOG: Tentando fazer INSERT na tabela Reviews")
            val resultSet = connection.executeQuery("INSERT INTO aplicacaoDB.Reviews ( idFilme, nmUsuario, classificacao, notaUsuario, comentario) VALUES ( ${review.idMovie}, ${review.nmUser}, ${review.rating}, \"${review.comment}\");")
            connection.close();
            return true
        }
        catch (exception:Exception){
            exception.printStackTrace()
            return false
        }
    }

    override fun insertMult(lista: List<Any>) : Boolean {
        val reviews = lista as List<Review>

        try {
            val connection = MariaDB()
            println(SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time) + " LOG: Tentando fazer INSERT na tabela Reviews")
            for (review : Review in reviews){
                val resultSet = connection.executeQuery("INSERT INTO aplicacaoDB.Reviews (idFilme, nmUsuario, classificacao, notaUsuario, comentario) VALUES (${review.idMovie}, ${review.nmUser}, ${review.rating}, \"${review.comment}\");")
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
        val review : Review = newObject as Review
        try{
            val connection = MariaDB()
            println(SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time) + " LOG: Tentando fazer UPDATE na tabela Reviews")
            val resultSet = connection.executeQuery("UPDATE aplicacaoDB.Reviews SET idFilme = ${review.idMovie}, nmUsuario = ${review.nmUser}, notaUsuario = ${review.rating}, comentario = \"${review.comment}\" WHERE id = ${id};")
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
            println(SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time) + " LOG: Tentando fazer DELETE na tabela Reviews")
            val resultSet = connection.executeQuery("DELETE FROM aplicacaoDB.Reviews WHERE id = ${id};")

            connection.close()
            return true
        }
        catch (exception:Exception){
            exception.printStackTrace()
            return false
        }
    }

    override fun getAllByIDMovie(id: Int): List<Any> {
        val reviews = mutableListOf<Review>()
        try {
            val connection = MariaDB()
            println(SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time) + " LOG: Tentando fazer SELECT na tabela Reviews")
            val resultSet = connection.executeQuery("SELECT * FROM aplicacaoDB.Reviews WHERE idFilme = $id;")

            while (resultSet!!.next()) {
                reviews.add(
                    Review(
                        resultSet.getInt("id"),
                        resultSet.getInt("idFilme"),
                        resultSet.getString("nmUsuario"),
                        resultSet.getFloat("notaUsuario"),
                        resultSet.getString("comentario")
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

    override fun existByUser(user: String): Any {
        TODO("Not yet implemented")
    }
}