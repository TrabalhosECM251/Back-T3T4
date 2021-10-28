package models

import models.enums.Classification

/*
    Model de review
    Atributos:
        id: int - id da review
        idFilm: int - id do filme avaliado
        idUser: int - id do usuário que fez a avaliação
        classification: Classification - classificação dada pelo usuário ao filme
        rating: float - Nota dada pelo usuário ao filme
        comment: string - Comentário da avaliação
 */

data class Review(val id: Int?, val idFilm: Int, val idUser: Int, val classification: Classification, val rating: Float,
                  val comment: String)