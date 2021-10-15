package repositories.mariadb

import java.sql.*

class MariaDB {
    var connection : Connection? = null
    var statement : Statement? = null
    var result : ResultSet? = null
    val user = "admin"
    val password = "admin"
    val host = "192.168.56.101"
    val port = "3306"

    init {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://${this.host}:${this.port}/", user,password)
            println("Conexão realizada com sucesso")
        } catch (e: Exception) {
            e.printStackTrace()
            println("Não foi possível conectar com o banco")
        }
    }

    fun executeQuery(sqlString: String): ResultSet?{
        this.statement = this.connection?.createStatement()
        this.result = this.statement?.executeQuery(sqlString);
        return this.result;
    }

    fun close(){
        this.result?.close();
        this.statement?.close();
        this.connection!!.close();
    }

}