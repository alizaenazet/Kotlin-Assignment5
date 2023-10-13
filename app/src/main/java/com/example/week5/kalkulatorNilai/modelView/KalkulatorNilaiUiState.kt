package com.example.week5.kalkulatorNilai.modelView

import com.example.week5.kalkulatorNilai.model.Subject

data class KalkulatorNilaiUiState(
    var sksTotal: Int = 0,
    var ipk: Double = 0.00,
    var subjects: HashMap<String, Subject> = HashMap<String, Subject>()
)
