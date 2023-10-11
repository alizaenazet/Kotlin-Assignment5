package com.example.week5.tebakAngka.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week5.tebakAngka.modelView.TebakAngkaViewModel
import java.lang.Math.min

fun gameResult(tebakAngkaViewModel: TebakAngkaViewModel): Boolean{
    return tebakAngkaViewModel.getData().isWin || tebakAngkaViewModel.getData().isLose
}

@Composable
fun showResult( succes: () -> Unit,
                setClose: (isOpen: Boolean)-> Unit,
                resetGuessesNumber: ()-> Unit,
                score: Int){
    AlertDialogExample(
        onDismissRequest = {
            setClose(false);
            resetGuessesNumber() },
        onConfirmation = {
            succes();
            setClose(false);
            resetGuessesNumber() },
        dialogTitle = "Welp ",
        dialogText = "Your score: ${score}"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun tebakAngka(){
    val tebakAngkaViewModel =  viewModel<TebakAngkaViewModel>()
    var openAlertDialog = remember { mutableStateOf(false) }
    var setAlertDialog: (isOpen: Boolean)-> Unit = {openAlertDialog.value = it}
    var guessInput by rememberSaveable { mutableStateOf("") }
    val setInput: (input:String)-> Unit = {guessInput = it}
    var guessesNumber by remember { mutableStateOf(0) }
    val resetGuessesNumber: () -> Unit = {guessesNumber = 0}
    val increseGuesses: ()-> Unit = { if (guessesNumber < 4 )guessesNumber += 1 }

    if (openAlertDialog.value){
        showResult(
            succes = { tebakAngkaViewModel.reStart() },
            setClose =  setAlertDialog,
            resetGuessesNumber = resetGuessesNumber,
            score = tebakAngkaViewModel.getData().totalScore)
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
                var guessText = "Number of guesses: ${guessesNumber}"
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

                Text(text = tebakAngkaViewModel.getData().angka.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp)

                Text(text = "From 1 until 30 ",
                    fontWeight = FontWeight.Bold,)
                Text(text = "Score: ${tebakAngkaViewModel.getData().totalScore}",
                    fontWeight = FontWeight.Bold,
                    )

                OutlinedTextField(
                    value = guessInput,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = setInput,
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
        Button(onClick = {
            tebakAngkaViewModel.insertGuess(guessInput.toInt())
            if (gameResult(tebakAngkaViewModel)){
                openAlertDialog.value = true;
            }
            increseGuesses()
        }, modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Blue)
            ) {
            Text(text = "Submit")
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun tebakAngkaPreview(){
    tebakAngka()
}