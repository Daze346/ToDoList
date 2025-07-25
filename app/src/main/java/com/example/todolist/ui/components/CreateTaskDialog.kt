package com.example.todolist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.R
import com.example.todolist.ui.theme.DarkGray
import com.example.todolist.ui.theme.LightGray

@Composable
fun CreateTaskDialog(
    modifier: Modifier,
    onClose: () -> Unit,
    createNote: (String, String) -> Unit
) {
    var edCreateNoteName by remember { mutableStateOf("") }
    var edCreateNoteDescription by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .background(color = DarkGray, shape = RoundedCornerShape(10.dp))
            .widthIn(max = 320.dp)
            .padding(all = 10.dp)
    ) {


        Text(
            modifier = Modifier
                .clickable{ onClose() },
            text = "Back",
            fontSize = 15.sp,
            fontFamily = FontFamily(Font(R.font.nunito_bold)),
            color = Color.White
        )


        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .heightIn(min = 40.dp)
                    .width(300.dp),
                value = edCreateNoteName,
                onValueChange = { edCreateNoteName = it },
                label = { Text(text = "Name of the task", color=LightGray) },
                shape = RoundedCornerShape(12.dp),

                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = LightGray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = LightGray,
                    cursorColor = LightGray
                ),
                singleLine = true
            )

            OutlinedTextField(
                modifier = Modifier
                    .heightIn(min = 40.dp, max = 80.dp)
                    .width(300.dp),
                value = edCreateNoteDescription,
                onValueChange = { edCreateNoteDescription = it },
                label = { Text(text = "Description of the task", color=LightGray) },
                shape = RoundedCornerShape(12.dp),

                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = LightGray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = LightGray,
                    cursorColor = LightGray
                )
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    if ( edCreateNoteName != "" ){
                        createNote(edCreateNoteName, edCreateNoteDescription)
                        edCreateNoteName = ""
                        edCreateNoteDescription = ""
                        onClose()
                    }

                },
                modifier = Modifier,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LightGray,
                    contentColor = Color.White
                )
            ) {
                Text("Create")
            }
        }


    }
}