package com.example.week5.tebakAngka.view

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week5.tebakAngka.modelView.TebakAngkaViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun tebakAngka(
    tebakAngkaViewModel: TebakAngkaViewModel = viewModel()
){
    val tebakAngkaUiState by tebakAngkaViewModel.uiState.collectAsState()

    if (tebakAngkaUiState.isOpenDialog){
        showResultDialog(
            reStartGame = { tebakAngkaViewModel.reStart() },
            score = tebakAngkaUiState.score)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = 15.dp),
            text = "Tebak angka 🫣",
            fontSize = 25.sp,
            fontWeight = FontWeight.Normal)
        Card(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Column (
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                var guessText = "Number of guesses: ${tebakAngkaViewModel.guessesNumber}"
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ){
                    Text(
                        modifier = Modifier
                            .background(Color.Blue, RoundedCornerShape(16.dp))
                            .padding(8.dp)
                        ,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        text = guessText)
                }

                Text(text = tebakAngkaViewModel.guessQuestion,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp)

                Text(text = tebakAngkaViewModel.interactionMessage,
                    fontWeight = FontWeight.Bold,)
                Text(text = "Score: ${tebakAngkaUiState.score}",
                    fontWeight = FontWeight.Bold,
                    )

                OutlinedTextField(
                    value = tebakAngkaViewModel.guessedNumber,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = { tebakAngkaViewModel.insertGuess(it) },
                    shape = RoundedCornerShape(15.dp),
                    label = { Text("Insert your input") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Blue,
                        textColor = Color.Black),
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }
        }
        Button(onClick = { tebakAngkaViewModel.onSubmitGuessClik() }, modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Blue)
            ) {
            Text(text = "Submit")
        }
    }

}


@Composable
fun showResultDialog(
    reStartGame: () -> Unit,
    score: Int){
    val activity = (LocalContext.current as? Activity)
    AlertDialogExample(
        onDismissRequest = {  activity?.finish()},
        onConfirmation = reStartGame ,
        dialogTitle = "Welp ",
        dialogText = "Your score: ${score}"
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun tebakAngkaPreview(){
    tebakAngka()
}
