package com.example.myapplication

import android.os.Bundle
import android.provider.CalendarContract.Reminders
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val random = remember {
                    mutableStateOf(Random.nextInt(100))
                }

                val x = remember {
                    mutableStateOf(0)
                }
                val Try = remember {
                    mutableStateOf("Try It")
                }
                val result = remember {
                    mutableStateOf("Result")
                }
                val names = remember {
                    mutableStateListOf<Int>()
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                )
                {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                            .background(color = Color(0xFF9C27B0)),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextField(
                            value = x.value.toString(),
                            onValueChange = {
                                x.value = it.toInt()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp)
                                .background(color = Color(0xFF9C27B0))
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                            .background(color = Color(0xFF9C27B0)),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(onClick = {
                            if (x.value == random.value) {
                                result.value = "Reset"
                                Try.value = "You Won!"
                            }
                            if (x.value < random.value) {
                                result.value = x.value.toString() + " is smaller"
                                names.add(x.value)
                                x.value = 0
                            }
                            if (x.value > random.value) {
                                result.value = x.value.toString() + " is larger"
                                names.add(x.value)
                                x.value = 0
                            }
                        }) {
                            Text(text = Try.value)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                            .background(color = Color(0xFF9C27B0)),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(onClick = {
                            if (result.value == "Reset") {
                                Try.value = "Try It"
                                x.value = 0
                                random.value = Random.nextInt(100)
                                names.clear()
                            }
                        }) {
                            Text(text = result.value)
                        }
                    }
                    AnimatedVisibility(visible = !names.isEmpty()) {

                        LazyColumn {
                            items(names) { name ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = "Number: $name",
                                        modifier = Modifier
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    if (name < random.value) {
                                        Text(
                                            text = "$name<Game Number",
                                            modifier = Modifier

                                        )
                                    }
                                    else if (name > random.value) {
                                        Text(
                                            text = "$name>Game Number",
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}