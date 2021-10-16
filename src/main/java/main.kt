import repositories.mariadb.MariaDB
import controllers.ktor.Ktor

fun main() {
    val kt = Ktor
    /*
    val db = MariaDB()

    var result = db.executeQuery("SELECT * FROM aplicacaoDB.Elementos;")

    while(result!!.next()){
        println("id: " + result.getInt("id"))
        println("nome: " + result.getString("nome"))
        println("tema: " + result.getString("tema"))
    }
    */
    Ktor.start()



}