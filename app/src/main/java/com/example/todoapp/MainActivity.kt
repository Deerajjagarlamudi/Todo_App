package com.example.todoapp

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.example.todoapp.ui.theme.TodoAppTheme
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            TodoAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ToDoListPage(modifier=Modifier.padding(innerPadding), viewModel =  todoViewModel)
                }

            }
        }
    }
}

@Composable
fun ToDoListPage(modifier: Modifier,viewModel: TodoViewModel){
    val toDoList by viewModel.todoList.observeAsState()

    var inputText by remember { mutableStateOf("") }
    Column (
        modifier = Modifier.fillMaxHeight()
            .border(
                width = 12.dp,
                color = MaterialTheme.colorScheme.primary
            )
    ){
        AppHeader()
        Spacer(modifier = Modifier.height(4.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            OutlinedTextField(value = inputText, onValueChange = {inputText=it}, modifier = Modifier.padding(8.dp),
                label = {Text(text="Enter Todo")})
            Button(onClick = {
                viewModel.addTodo(inputText)
                inputText = ""

            }) {
                Text("Add")
            }
        }

        toDoList?.let {
            LazyColumn(content = {
                itemsIndexed(it.reversed()){ index : Int, item: list ->
                    TodoItem(item=item, onDelete = {viewModel.deleteTodo(item.id)})
                }
            })

        }?: Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, text="No Items Yet")


    }
}

@Composable
fun TodoItem(item:list, onDelete: ()->Unit){
    Row( modifier = Modifier.fillMaxWidth()
        .padding(16.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(MaterialTheme.colorScheme.primary)
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = SimpleDateFormat("HH:mm:aa", Locale.ENGLISH).format(item.createdOn),
                fontSize = 16.sp,
                color = Color.Gray)
            Text(text = item.name, fontSize = 20.sp, color = Color.White)

        }
        IconButton(onClick = onDelete) {
            Icon(painter = painterResource(R.drawable.outline_delete_24), contentDescription ="Delete", tint = Color.White )
        }

    }
}


@Composable
fun AppHeader(){
    Box(
        modifier = Modifier.fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ){
        Text(text = "",
            modifier = Modifier.padding(16.dp),
            fontSize = 22.sp,
            color = Color.White)
    }
}


