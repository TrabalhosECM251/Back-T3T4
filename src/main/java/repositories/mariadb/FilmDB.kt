package main.java.repositories.mariadb

import main.java.interfaces.IRepo

/*
    Classe responsável por declarar métodos de manipulação do DB de User
 */

class FilmDB : IRepo {
    override fun getOne(id: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<Any> {
        TODO("Not yet implemented")
    }

    override fun insertOne(objeto: Any) {
        TODO("Not yet implemented")
    }

    override fun insertMult(lista: List<Any>) {
        TODO("Not yet implemented")
    }

    override fun update(id: Int, newObject: Any) {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }
}