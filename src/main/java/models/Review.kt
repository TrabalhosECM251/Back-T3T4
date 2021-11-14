package models

/*
    Model de review
    Atributos:
        id: int - id da review
        idMovie: int - id do Moviee avaliado
        idUser: int - id do usuário que fez a avaliação
        classification: Classification - classificação dada pelo usuário ao Moviee
        rating: float - Nota dada pelo usuário ao Moviee
        comment: string - Comentário da avaliação
 */

data class Review(val id: Int? = null, val idMovie: Int, val nmUser: String, val rating: Float,
                  val comment: String)