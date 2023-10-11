package com.example.week5.tebakAngka.model

import kotlin.random.Random

class GameStatus {
    val angka: Int = Random.nextInt(1,30)
    var guesses = 0
    var isWin = false
    var isLose = false
    var totalScore = 0

    fun setWin(){
        isWin = true
    }

    fun setLose(){
        isLose = true
    }

    fun increseGuesses():Boolean{
        if (this.guesses == 3){
            return  false
        }
        guesses += 1
        return  true
    }

}