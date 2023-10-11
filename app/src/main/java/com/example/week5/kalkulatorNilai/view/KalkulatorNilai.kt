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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week5.kalkulatorNilai.model.Subject
import com.example.week5.kalkulatorNilai.modelView.KalkulatorNilaiViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun kalkulatorNilai(){
    val kalkulatorNilaiViewModel = viewModel<KalkulatorNilaiViewModel>()

    var subjects by rememberSaveable { mutableStateOf(HashMap<String,Subject>()) }
    val initSubjects: (HashMap<String,Subject>)-> Unit = {subjects = it};
    var sksInput by remember { mutableStateOf("") }
    val setSks : (sks: String)-> Unit = {sksInput = it}
    var scoreInput by remember { mutableStateOf("") }
    val setScore: (score: String)-> Unit = {scoreInput = it}
    var nameInput by remember { mutableStateOf("") }
    val setName: (name: String)-> Unit = {nameInput = it}
    val deleteSubject: (name:String)-> Unit = { kalkulatorNilaiViewModel.removeSubject(it);
        initSubjects(kalkulatorNilaiViewModel.getData().subjects) }

    LazyColumn(
        contentPadding = PaddingValues(16.dp, 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        item { 
            Text(text = "Courses", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }

        item {
            Text(text = "Total SKS: ${kalkulatorNilaiViewModel.getData().sksTotal}")
        }

        item {
            Text(text = "IPK: ${kalkulatorNilaiViewModel.getData().ipk}")
        }

        item {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ){
                OutlinedTextField(
                    value = sksInput,
                    onValueChange = setSks,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(text = "SKS")},
                    modifier = Modifier
                        .weight(1f)
                )
                OutlinedTextField(
                    value = scoreInput,
                    onValueChange = setScore,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(text = "Score")},
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }

        item { 
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(value = nameInput,
                    onValueChange = setName,
                    label = { Text(text = "Name")},
                    modifier = Modifier
                )
                Button(
                    onClick = {
                              kalkulatorNilaiViewModel.addNewSubject(
                                  nameInput,
                                  sksInput.toInt(),
                                  scoreInput.toDouble());
                            initSubjects(kalkulatorNilaiViewModel.getData().subjects)
                              },
                    modifier = Modifier
                        .fillMaxHeight()) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "ADD icon")
                }
            }
        }

        items(ArrayList(subjects.values)){subject ->
            subjectCard(subject = subject, deleteSubject = deleteSubject)
        }


    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun kalkulatorNilaiPreview(){
    kalkulatorNilai()
}