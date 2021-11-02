package main.java.repositories.mariadb

import main.java.interfaces.IRepo
import models.Film
import repositories.mariadb.MariaDB

/*
    Classe responsável por declarar métodos de manipulação do DB de User
 */

class FilmDB : IRepo {
    override fun getOne(id: Int): Any {
        val film : Film? = null
        try {

            val connection = MariaDB();

            val resultSet = connection.executeQuery("SELECT * FROM aplicacaoDB.Filmes WHERE id = ${id};");

            if (resultSet != null) {
                while (resultSet.next()){
                    val film = Film(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("tema"),
                        resultSet.getString("disponivel"),
                        resultSet.getFloat("nota"),
                        resultSet.getString("poster")
                    )
                }
            }
            resultSet!!.close();
            connection.close();
        }
        catch (exception:Exception){
            exception.printStackTrace()
        }
        return film!!
    }

    override fun getAll(): List<Any> {
        val films = mutableListOf<Film>()
        try {
            //Cria uma conexão com o banco
            val connection = MariaDB()
            val resultSet = connection.executeQuery("SELECT * FROM aplicacaoDB.Filmes;")
            //Intera pelo resultado obtido

            while (resultSet?.next()!!) {
                films.add(
                    Film(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("tema"),
                        resultSet.getString("disponivel"),
                        resultSet.getFloat("nota"),
                        resultSet.getString("poster")
                    )
                )
            }
            resultSet!!.close();
            connection.close();
        }
        catch (exception:Exception){
            exception.printStackTrace()

        }
        return films;

    }

    override fun insertOne(objeto: Any) {

        val film : Film = objeto as Film;

        try {
            val connection = MariaDB();
            val resultSet = connection.executeQuery("INSERT INTO Filmes (nome, tema, disponivel, nota) VALUES ('${film.name}', '${film.theme}', '${film.available}', ${film.rating}, '${film.poster}');")
            resultSet!!.close();
            connection.close();
        }
        catch (exception:Exception){
            exception.printStackTrace()
        }
    }

    override fun insertMult(lista: List<Any>) {
        val films = mutableListOf<Film>()

        try {
            val connection = MariaDB();
            for (film : Film in films){
                val resultSet = connection.executeQuery("INSERT INTO Filmes (nome, tema, disponivel, nota) VALUES ('${film.name}', '${film.theme}', '${film.available}', ${film.rating}, '${film.poster}');")
            }
            connection.close();
        }
        catch (exception:Exception){
            exception.printStackTrace()
        }

    }

    override fun update(id: Int, newObject: Any) {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }
}