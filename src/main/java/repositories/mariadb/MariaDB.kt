package repositories.mariadb

import java.sql.*
import config.MariaDB
import java.text.SimpleDateFormat
import java.util.*

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
            println(SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time) + " LOG: Iniciando conexão com banco")
            connection = DriverManager.getConnection("jdbc:mysql://${this.host}:${this.port}/", user,password)
            println(SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time) + " LOG: Conexão realizada com sucesso")
        } catch (e: Exception) {
            e.printStackTrace()
            println(SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time) + " LOG Não foi possível conectar com o banco")
        }
    }

    fun executeQuery(sqlString: String): ResultSet?{
        this.statement = this.connection?.createStatement()
        println(SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time) + " LOG: Executando query ($sqlString)")
        this.result = this.statement?.executeQuery(sqlString)
        println(SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time) + " LOG: Query executada com sucesso")
        return this.result
    }

    fun close(){
        println(SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time) + " LOG: Fechando conexão com o banco")
        this.result?.close()
        this.statement?.close()
        this.connection!!.close()
        println(SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().time) + " LOG: Conexão com banco fechada")
    }

}