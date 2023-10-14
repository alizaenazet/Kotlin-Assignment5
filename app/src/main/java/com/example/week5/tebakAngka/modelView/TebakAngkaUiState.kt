package com.example.week5.tebakAngka.modelView

import kotlin.random.Random

data class TebakAngkaUiState(
    val currentGuessNumber: Int = Random.nextInt(0,10),
    val isOpenDialog: Boolean = false,
    val score: Int = 0
)
