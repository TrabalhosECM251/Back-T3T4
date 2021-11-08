package main.java.repositories.mariadb

import main.java.interfaces.IRepo
import models.Film
import repositories.mariadb.MariaDB

/*
    Classe responsável por declarar métodos de manipulação do DB de User
 */

class FilmDB : IRepo {
    override fun getOne(id: Int): Any {
        var film : Film? = null

        try {
            val connection = MariaDB();

            val resultSet = connection.executeQuery("SELECT * FROM aplicacaoDB.Filmes WHERE id = ${id};");

            while (resultSet!!.next()){
                film = Film(
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
        return film!!
    }

    override fun getAll(): List<Any> {
        val films = mutableListOf<Film>()
        try {

            val connection = MariaDB()
            val resultSet = connection.executeQuery("SELECT * FROM aplicacaoDB.Filmes;")

            while (resultSet!!.next()) {
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
            connection.close();
        }
        catch (exception:Exception){
            exception.printStackTrace()
        }

        return films;
    }

    override fun insertOne(objeto: Any) : Boolean {

        val film : Film = objeto as Film;

        try {
            val connection = MariaDB();
            val resultSet = connection.executeQuery("INSERT INTO aplicacaoDB.Filmes (nome, tema, disponivel, nota, poster) VALUES ('${film.name}', '${film.theme}', '${film.available}', ${film.rating}, '${film.poster}');")
            connection.close();
            return true
        }
        catch (exception:Exception){
            exception.printStackTrace()
            return false
        }
    }

    override fun insertMult(lista: List<Any>) : Boolean{
        val films = lista as List<Film>

        try {
            val connection = MariaDB();
            for (film : Film in films){
                val resultSet = connection.executeQuery("INSERT INTO aplicacaoDB.Filmes (nome, tema, disponivel, nota, poster) VALUES ('${film.name}', '${film.theme}', '${film.available}', ${film.rating}, '${film.poster}');")
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
            val film : Film = newObject as Film

            val resultSet = connection.executeQuery("UPDATE aplicacaoDB.Filmes SET nome = '${film.name}', tema = '${film.theme}', disponivel = '${film.available}', nota = ${film.rating}, poster = '${film.poster}' WHERE id = ${id};")

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