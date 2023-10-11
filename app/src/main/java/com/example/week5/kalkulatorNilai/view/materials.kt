package com.example.week5.kalkulatorNilai.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.week5.kalkulatorNilai.model.Subject

@Composable
fun subjectCard(subject: Subject, deleteSubject: (String)->Unit){
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column (
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalAlignment = Alignment.Start
                ){
                    Text(text = "Name: ${subject.name}",
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "SKS: ${subject.sks}")
                    Text(text = "Score: ${subject.score}")
                }
                Button(
                    onClick = { deleteSubject(subject.name)},
                    colors = ButtonDefaults.buttonColors(Color.Transparent)) {
                        Icon(imageVector = Icons.Filled.Delete,
                            contentDescription = "DELETE icon",
                            tint = Color.Red)
                }
            }
        }
}