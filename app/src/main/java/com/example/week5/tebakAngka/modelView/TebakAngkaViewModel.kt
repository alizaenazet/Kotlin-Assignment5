package com.example.week5.tebakAngka.modelView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week5.tebakAngka.model.GameStatus
import kotlinx.coroutines.launch
import java.lang.Exception

sealed interface TebakAngkaUIState{
    data class  Success(val data: GameStatus):TebakAngkaUIState
    object Error: TebakAngkaUIState
    object Loading: TebakAngkaUIState
}



class TebakAngkaViewModel : ViewModel (){
    var tebakAngkaUIState: TebakAngkaUIState by mutableStateOf(TebakAngkaUIState.Loading)
        private set;

    private lateinit var data: GameStatus
    init {
        loadData()
    }

    fun loadData(){
        viewModelScope.launch {
            try {
                data = GameStatus()
                tebakAngkaUIState = TebakAngkaUIState.Success(data)
            }catch (e: Exception){
                tebakAngkaUIState = TebakAngkaUIState.Error
            }
        }
    }

    fun insertGuess(numberOfGuess: Int) : Boolean{
        if (data.angka == numberOfGuess){
            data.setWin()
        }

        if (!data.increseGuesses()){
            data.setLose()
            return false
        }

        when(data.guesses){
            1 -> data.totalScore = 3
            2 -> data.totalScore = 2
            3 -> data.totalScore = 1
            4 -> data.totalScore = 0
        }
        return true
    }

    fun getData(): GameStatus{
        return data
    }

    fun reStart(){
        try {
            data = GameStatus()
            tebakAngkaUIState = TebakAngkaUIState.Success(data)
        }catch (e: Exception){
            tebakAngkaUIState = TebakAngkaUIState.Error
        }
    }


}