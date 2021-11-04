package repositories.mariadb

import java.sql.*
import config.MariaDB

/*
    Classe responsável pela conexão com o banco de dados MariaDB
 */

class MariaDB {
    var connection : Connection? = null
    var statement : Statement? = null
    var result : ResultSet? = null
    private val user = MariaDB.user
    private val password = MariaDB.password
    private val host = MariaDB.host
    private val port = MariaDB.port

    init {
        try {
            Class.forName("org.mariadb.jdbc.Driver")
            connection = DriverManager.getConnection("jdbc:mysql://${this.host}:${this.port}/", user,password)
            println("Conexão realizada com sucesso")
        } catch (e: Exception) {
            e.printStackTrace()
            println("Não foi possível conectar com o banco")
        }
    }

    fun executeQuery(sqlString: String): ResultSet?{
        this.statement = this.connection?.createStatement()
        this.result = this.statement?.executeQuery(sqlString)
        return this.result
    }

    fun close(){
        this.result?.close()
        this.statement?.close()
        this.connection!!.close()
    }

}