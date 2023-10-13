package com.example.week5.kalkulatorNilai.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week5.kalkulatorNilai.modelView.KalkulatorNilaiViewModel


@Composable
fun kalkulatorNilai(
    kalkulatorNilaiViewModel: KalkulatorNilaiViewModel = viewModel()
){
    val kalkulatorNilaiUiState by kalkulatorNilaiViewModel.uiState.collectAsState()

    LazyColumn(
        contentPadding = PaddingValues(16.dp, 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        item { 
            Text(text = "Courses", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }

        item {
            Text(text = "Total SKS: ${kalkulatorNilaiUiState.sksTotal}")
        }

        item {
            Text(text = "IPK: ${kalkulatorNilaiUiState.ipk}")
        }

        item {
            sksAndIpkInput(
                userSksInput = kalkulatorNilaiViewModel.userSksInput,
                onSksChange = { kalkulatorNilaiViewModel.onSksInputChange(it) },
                userScoreInput = kalkulatorNilaiViewModel.userScoreInput,
                onScoreChange = { kalkulatorNilaiViewModel.onScoreInputChange(it) }
            )
        }

        item {
            nameAndButtonRow(
                userNameInput = kalkulatorNilaiViewModel.userNameInput,
                onNameInputChange = { kalkulatorNilaiViewModel.onNameInputChange(it) },
                onAddSubjectClick = { kalkulatorNilaiViewModel.onAddSubjectClik() }
            )
        }

        items(ArrayList(kalkulatorNilaiUiState.subjects.values)){subject ->
            subjectCard(subject = subject, deleteSubject = { kalkulatorNilaiViewModel.removeSubject(it) } )
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun sksAndIpkInput(
    userSksInput: String,
    onSksChange: (String) -> Unit,
    userScoreInput: String,
    onScoreChange: (String) -> Unit
){
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
    ){
        OutlinedTextField(
            value = userSksInput,
            onValueChange = onSksChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(text = "SKS")},
            modifier = Modifier
                .weight(1f)
        )
        OutlinedTextField(
            value = userScoreInput,
            onValueChange = onScoreChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(text = "Score")},
            modifier = Modifier
                .weight(1f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun nameAndButtonRow(
    userNameInput: String,
    onNameInputChange: (String)-> Unit,
    onAddSubjectClick: ()-> Unit
){
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(value = userNameInput,
            onValueChange = onNameInputChange,
            label = { Text(text = "Name")},
            modifier = Modifier
        )
        Button(
            onClick = { onAddSubjectClick() },
            modifier = Modifier
                .fillMaxHeight()) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "ADD icon")
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun kalkulatorNilaiPreview(){
    kalkulatorNilai()
}