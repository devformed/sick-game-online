package com.devformed.sickgame.dto

data class QuizDto(
    val points: Int,
    val question: QuestionDto,
    val answer: String
)