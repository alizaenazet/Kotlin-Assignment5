package com.example.week5.tebakAngka.modelView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random


class TebakAngkaViewModel : ViewModel (){
    private val _uiState = MutableStateFlow(TebakAngkaUiState())
    val uiState : StateFlow<TebakAngkaUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private var _userGuessedNumberInput by mutableStateOf("")
    val guessedNumber: String
        get() = _userGuessedNumberInput;

    private var _guessesNumber: Int = 0
    val guessesNumber: Int
        get() = _guessesNumber;

    private var _guessQuestion :String = createMathGuess()
    val guessQuestion: String
        get() =  _guessQuestion

    private var _interactionMessage = "Ayo bisa doong🤗❤️"
    val interactionMessage
        get() = _interactionMessage

    fun loadData(){
        reStart()
    }

    fun insertGuess(numberOfGuess: String){
        _userGuessedNumberInput = numberOfGuess
    }

    fun onSubmitGuessClik(){
        _guessesNumber+=1


        if (_userGuessedNumberInput.toInt() == _uiState.value.currentGuessNumber.toInt()){
            _uiState.update {
                it.copy(
                    score = (_uiState.value.score + 1),
                )
            }
            regenerateNumber()
            _interactionMessage = "aah cuma kebetulan 😛"
        }else{
            _interactionMessage = "wkwkwk salah 😂🥱"
        }

        if (_guessesNumber >= 3){
            _uiState.update {
                it.copy(
                    isOpenDialog = true
                )
            }
        }
        _userGuessedNumberInput = ""
    }


    private fun regenerateNumber(){
        _guessQuestion = createMathGuess()
    }


    fun reStart(){
        _uiState.value = TebakAngkaUiState()
        _guessesNumber = 0
        regenerateNumber()
        _interactionMessage = "tantangin lagi nich 😱❤️"
    }
    fun createMathGuess(): String {
        val operators = arrayOf("+", "-", "*", "/")
        var result: String = ""
        var resultNumber: Int = 0
        // Pilih operator acak
        val operator = operators[Random.nextInt(operators.size)]

        // Pilih operand acak
        val operand1 = Random.nextInt(1, 20) // Anda bisa menyesuaikan batasan sesuai kebutuhan
        val operand2 = Random.nextInt(1, 20) // Anda bisa menyesuaikan batasan sesuai kebutuhan
        val operand4 = Random.nextInt(1, 20) // Anda bisa menyesuaikan batasan sesuai kebutuhan
        val operand3 = Random.nextInt(1, 20) // Anda bisa menyesuaikan batasan sesuai kebutuhan

        // Hitung hasil operasi
         when (operator) {
            "+" -> {
                resultNumber = operand1 + operand2 * operand3 / operand4
                result = "$operand1 + $operand2 * $operand3 / $operand4 "
            }
            "-" -> {
                resultNumber = operand1 - operand2 * operand3 / operand4
                result = "$operand1 - $operand2 * $operand3 / $operand4 "
            }
            "*" -> {
                resultNumber = operand1 * operand2 - operand3 / operand4
                result = "$operand1 * $operand2 - $operand3 / $operand4 "
            }
            "/" -> {
                resultNumber = operand1 * operand2 * operand3 / operand4
                result = "$operand1 * $operand2 * $operand3 / $operand4"
            }
            else -> {
                resultNumber = operand1 + operand2 - operand3 / operand4
                result = "$operand1 + $operand2 - $operand3 / $operand4"
            }
        }
        _uiState.update {
            it.copy(
                currentGuessNumber = resultNumber
            )
        }
        return result
    }


}