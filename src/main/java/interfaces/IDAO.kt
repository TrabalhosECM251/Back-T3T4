package main.java.interfaces

interface IDAO{
    fun getOne(id:Int): Any
    fun getAll(): List<Any>
    fun insertOne(objeto: Any)
    fun insertMult(lista: List<Any>)
    fun update(objeto: Any)
    fun delete(id: Int)
}