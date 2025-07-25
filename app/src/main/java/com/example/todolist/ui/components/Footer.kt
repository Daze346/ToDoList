package com.example.todolist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.R
import com.example.todolist.ui.theme.LightBlack

@Composable
fun Footer(onNewNoteClick: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = LightBlack)
            .padding(top = 5.dp, end = 10.dp),
        horizontalArrangement = Arrangement.End,
    ) {
        Text(
            text = "New task",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable { onNewNoteClick() },
            fontFamily = FontFamily(Font(R.font.nunito_bold))
        )
    }
}