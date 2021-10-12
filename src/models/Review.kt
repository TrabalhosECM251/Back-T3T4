package models

import models.enums.Classification

data class Review(val id: Int, val idFilm: Int, val idUser: Int, val classification: Classification, val rating: Float,
                  val comment: String)