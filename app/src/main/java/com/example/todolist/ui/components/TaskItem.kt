package com.example.todolist.ui.components

import Task
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.R
import com.example.todolist.ui.theme.DarkGray

@Composable
fun TaskItem(task: Task, onCheckedChange: (Boolean) -> Unit, onDelete: (Task) -> Unit) {

    Row(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp)
            .background(
                color = DarkGray,
                shape = RoundedCornerShape(8.dp)
            ),
    ){
        Row(
            modifier = Modifier.padding(end = 10.dp, top = 5.dp, bottom = 5.dp)
        ){


            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                Checkbox(
                    modifier = Modifier.size(48.dp),
                    checked = task.isCompleted,
                    onCheckedChange = onCheckedChange,
                    colors = CheckboxDefaults.colors(
                        checkedColor = Transparent,
                        uncheckedColor = Color.White,
                        checkmarkColor = Color.Green,
                    )
                )


                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(onClick = { onDelete(task) })
                        .background(Transparent),
                    tint = Color.White
                )


            }



            Column(
            ) {

                Text(
                    modifier = Modifier.widthIn(max = 250.dp),
                    text = task.title,
                    color = Color.White,
                    fontSize = 25.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_bold))
                )

                Text(
                    modifier = Modifier.widthIn(max = 250.dp),
                    text = task.description,
                    color = Color.LightGray,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_bold))
                )

            }





        }
    }


}