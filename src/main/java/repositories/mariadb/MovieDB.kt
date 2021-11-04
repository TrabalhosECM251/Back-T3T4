package main.java.repositories.mariadb

import main.java.interfaces.IRepo
import models.Movie
import repositories.mariadb.MariaDB

/*
    Classe responsável por declarar métodos de manipulação do DB de User
 */

class MovieDB : IRepo {
    override fun getOne(id: Int): Any {
        var Movie : Movie? = null

        try {
            val connection = MariaDB();

            val resultSet = connection.executeQuery("SELECT * FROM aplicacaoDB.Filmes WHERE id = ${id};");

            while (resultSet!!.next()){
                Movie = Movie(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("tema"),
                    resultSet.getString("disponivel"),
                    resultSet.getFloat("nota"),
                    resultSet.getString("poster")
                )
            }
            connection.close();

        }
        catch (exception:Exception){
            exception.printStackTrace()
        }
        return Movie!!
    }

    override fun getAll(): List<Any> {
        val Movies = mutableListOf<Movie>()
        try {

            val connection = MariaDB()
            val resultSet = connection.executeQuery("SELECT * FROM aplicacaoDB.Filmes;")

            while (resultSet!!.next()) {
                Movies.add(
                    Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("tema"),
                        resultSet.getString("disponivel"),
                        resultSet.getFloat("nota"),
                        resultSet.getString("poster")
                    )
                )
            }
            connection.close();
        }
        catch (exception:Exception){
            exception.printStackTrace()
        }

        return Movies;
    }

    override fun insertOne(objeto: Any) : Boolean {

        val Movie : Movie = objeto as Movie;

        try {
            val connection = MariaDB();
            val resultSet = connection.executeQuery("INSERT INTO aplicacaoDB.Filmes (nome, tema, disponivel, nota, poster) VALUES ('${Movie.name}', '${Movie.theme}', '${Movie.available}', ${Movie.rating}, '${Movie.poster}');")
            connection.close();
            return true
        }
        catch (exception:Exception){
            exception.printStackTrace()
            return false
        }
    }

    override fun insertMult(lista: List<Any>) : Boolean{
        val Movies = lista as List<Movie>

        try {
            val connection = MariaDB();
            for (Movie : Movie in Movies){
                val resultSet = connection.executeQuery("INSERT INTO aplicacaoDB.Filmes (nome, tema, disponivel, nota, poster) VALUES ('${Movie.name}', '${Movie.theme}', '${Movie.available}', ${Movie.rating}, '${Movie.poster}');")
            }
            connection.close();
            return true
        }
        catch (exception:Exception){
            exception.printStackTrace()
            return false
        }

    }

    override fun update(id: Int, newObject: Any) : Boolean{
        try{
            val connection = MariaDB()
            val Movie : Movie = newObject as Movie

            val resultSet = connection.executeQuery("UPDATE aplicacaoDB.Filmes SET nome = '${Movie.name}', tema = '${Movie.theme}', disponivel = '${Movie.available}', nota = ${Movie.rating}, poster = '${Movie.poster}' WHERE id = ${id};")

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

            val resultSet = connection.executeQuery("DELETE FROM aplicacaoDB.Filmes WHERE id = ${id};")

            connection.close()
            return true
        }
        catch (exception:Exception){
            exception.printStackTrace()
            return false
        }

    }
}