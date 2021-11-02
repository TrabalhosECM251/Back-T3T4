package main.java.repositories.mariadb

import main.java.interfaces.IRepo
import models.Review
import models.enums.Classification
import repositories.mariadb.MariaDB

/*
    Classe responsável por declarar métodos de manipulação do DB de Reviews
 */

class ReviewDB : IRepo {
    override fun getOne(id: Int): Any {
        var review : Review? = null

        try {
            val connection = MariaDB();

            val resultSet = connection.executeQuery("SELECT * FROM aplicacaoDB.Reviews WHERE id = ${id};");

            while (resultSet!!.next()){
                review = Review(
                    resultSet.getInt("id"),
                    resultSet.getInt("idFilme"),
                    resultSet.getInt("idUsuario"),
                    Classification.valueOf(resultSet.getString("classificacao")),
                    resultSet.getFloat("nota"),
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

    override fun getAll(): List<Any> {
        val reviews = mutableListOf<Review>()
        try {

            val connection = MariaDB()
            val resultSet = connection.executeQuery("SELECT * FROM aplicacaoDB.Reviews;")

            while (resultSet!!.next()) {
                reviews.add(
                    Review(
                        resultSet.getInt("id"),
                        resultSet.getInt("idFilme"),
                        resultSet.getInt("idUsuario"),
                        Classification.valueOf(resultSet.getString("classificacao")),
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
            val resultSet = connection.executeQuery("INSERT INTO aplicacaoDB.Reviews ( idFilme, idUsuario, classificacao, notaUsuario, comentario) VALUES ( ${review.idFilm}, ${review.idUser}, '${review.classification}', ${review.rating}, ${review.comment});")
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
            val connection = MariaDB();
            for (review : Review in reviews){
                val resultSet = connection.executeQuery("INSERT INTO aplicacaoDB.Reviews (idFilme, idUsuario, classificacao, notaUsuario, comentario) VALUES (${review.idFilm}, ${review.idUser}, '${review.classification}', ${review.rating}, '${review.comment}');")
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

            val resultSet = connection.executeQuery("UPDATE aplicacaoDB.Reviews SET idFilme = ${review.idFilm}, idUsuario = ${review.idUser}, classificacao = '${review.classification}', notaUsuario = ${review.rating}, comentario = ${review.comment} WHERE id = ${id};")
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

            val resultSet = connection.executeQuery("DELETE FROM aplicacaoDB.Reviews WHERE id = ${id};")

            connection.close()
            return true
        }
        catch (exception:Exception){
            exception.printStackTrace()
            return false
        }
    }
}