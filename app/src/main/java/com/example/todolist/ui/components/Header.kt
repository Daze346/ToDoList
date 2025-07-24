package com.example.todolist.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.todolist.ui.theme.LightGray

@Composable
fun Header(){

    var edHeaderSearch by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Text(
            text = "Notes",
            fontSize = 35.sp,
            fontFamily = FontFamily(Font(R.font.nunito_bold)),
            color = Color.White

        )

        OutlinedTextField(
            modifier = Modifier
                .heightIn(min = 40.dp)
                .width(300.dp),
            value = edHeaderSearch,
            onValueChange = { edHeaderSearch = it },
            label = { Text(text = "Search", color = LightGray) },
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

    }

}