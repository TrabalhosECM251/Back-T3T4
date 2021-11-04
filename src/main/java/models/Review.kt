package models

import models.enums.Classification

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

data class Review(val id: Int?, val idMovie: Int, val idUser: Int, val classification: Classification, val rating: Float,
                  val comment: String)