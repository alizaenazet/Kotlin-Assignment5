package com.example.week5.kalkulatorNilai.modelView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.week5.kalkulatorNilai.model.Courses
import com.example.week5.kalkulatorNilai.model.Subject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update






class KalkulatorNilaiViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(KalkulatorNilaiUiState())
    val uiState: StateFlow<KalkulatorNilaiUiState> = _uiState.asStateFlow()

    private lateinit var data: Courses

    var userSksInput by mutableStateOf("")
        private set
    var userScoreInput by mutableStateOf("")
        private set
    var userNameInput by mutableStateOf("")
        private set


    init {
        startPage()
    }

    private fun startPage(){
                data = Courses(0,0.00)
                _uiState.value = KalkulatorNilaiUiState(
                    ipk = 0.00,
                    sksTotal = 0,
                    subjects = HashMap<String, Subject>()
                    )
    }

    private fun addNewSubject(name: String, sks: Int, score: Double){
        data.addSubject(Subject(name,sks,score))
        data.calculateCourse()
        var tempSubject : HashMap<String, Subject> = uiState.value.subjects
        tempSubject.put(name, Subject(name,sks,score))
        _uiState.update { currentState-> currentState.copy(subjects = tempSubject) }
        calculateCourse()
    }

    private fun calculateCourse(){
        if (uiState.value.subjects.isNotEmpty()) {
            var tempSksTotal = 0
            var tempScoreTotal: Double = 0.00

            uiState.value.subjects.forEach { name, subject ->
                tempSksTotal += subject.sks
                tempScoreTotal += (subject.score * subject.sks)
            }

            if (tempSksTotal == 0) {
                _uiState.update { currentState ->
                    currentState.copy(sksTotal = 0, ipk = 0.00)
                }
            } else {
                _uiState.update { currentState ->
                    currentState.copy(
                        sksTotal = tempSksTotal,
                        ipk = tempScoreTotal
                    )
                }
            }
        }else{
            _uiState.update { currentState ->
                currentState.copy(sksTotal = 0, ipk = 0.00)
            }
        }
    }
    fun removeSubject(name: String){
        data.deleteSubject(name)
        data.calculateCourse()
        var tempSubject : HashMap<String, Subject> = uiState.value.subjects
        tempSubject.remove(name)
        _uiState.update { currentState-> currentState.copy(
            subjects = tempSubject
        ) }
        calculateCourse()
    }

    fun onSksInputChange(sksInput: String){
        userSksInput = sksInput
    }

    fun onScoreInputChange(scoreInput: String){
        userScoreInput = scoreInput
    }

    fun onNameInputChange(nameInput: String){
        userNameInput = nameInput
    }

    fun onAddSubjectClik(){
        addNewSubject(userNameInput,userSksInput.toInt(),userScoreInput.toDouble())
        userNameInput = ""
        userScoreInput = ""
        userSksInput = ""
    }

}