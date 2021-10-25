package dao

interface GenericDAO{
    fun getOne(id:Int): Any
    fun getAll(): List<Any>
    fun insertOne(objeto: Any)
    fun insertMult(lista: List<Any>)
    fun update(objeto: Any)
    fun delete(id: Int)
}