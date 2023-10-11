package com.example.week5.kalkulatorNilai.modelView

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week5.kalkulatorNilai.model.Courses
import com.example.week5.kalkulatorNilai.model.Subject
import kotlinx.coroutines.launch

sealed interface KalkulatorNilaiUIState{
    data class Success(val data: Courses):KalkulatorNilaiUIState
    object Error: KalkulatorNilaiUIState
    object Loading: KalkulatorNilaiUIState
}

class KalkulatorNilaiViewModel: ViewModel() {

    var kalkulatorNilaiUIState: KalkulatorNilaiUIState by mutableStateOf(KalkulatorNilaiUIState.Loading)
        private set

    private lateinit var data: Courses

    init {
        loadData()
    }

    fun loadData(){
        viewModelScope.launch {
            try {
                data = Courses(0,0.00)
                kalkulatorNilaiUIState = KalkulatorNilaiUIState.Success(data)
            }catch (e: Exception){
                kalkulatorNilaiUIState = KalkulatorNilaiUIState.Error
            }
        }
    }

    fun addNewSubject(name: String, sks: Int, score: Double){
        data.addSubject(Subject(name,sks,score))
        data.calculateCourse()
    }

    fun removeSubject(name: String){
        data.deleteSubject(name)
        data.calculateCourse()
    }

    fun getData(): Courses{
        return data
    }

}