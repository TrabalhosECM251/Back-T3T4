package main.java.interfaces


/*
    Interface de métodos que devem ser implementados por um repositório
 */

interface IRepo{
    fun getOne(id:Int): Any
    fun getAll(objeto: Any?): List<Any>
    fun insertOne(objeto: Any): Boolean
    fun insertMult(lista: List<Any>): Boolean
    fun update(id: Int, newObject: Any): Boolean
    fun delete(id: Int): Boolean
    fun getAllByIDMovie(id: Int): List<Any>
    fun existByUser(user: String): Any
}