package com.example.lab_7

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun UpcomingRacesScreen(navController: NavController) {
    var input by remember { mutableStateOf("") }
    val entries = remember { mutableStateListOf<String>() }

    Column(modifier = Modifier.fillMaxSize().imePadding().padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = { navController.popBackStack() }, modifier = Modifier.testTag("backButton")) {
                Text("Back")
            }
            
        }
        Spacer(Modifier.height(16.dp))
        Text("Upcoming Races", fontSize = 30.sp)
        Spacer(Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = input,
                onValueChange = { input = it },
                label = { Text("Race Location") },
                singleLine = true,
                modifier = Modifier.weight(1f).testTag("locationInput")
            )
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = {
                    if (input.isNotBlank()) {
                        entries.add(input.trim())
                        input = ""
                    }
                },
                modifier = Modifier.testTag("addRaceButton")
            ) { Text("Add") }
        }
        LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth().testTag("racesList")) {
            items(entries) { entry ->
                Text(entry, fontSize = 24.sp, modifier = Modifier.padding(top = 12.dp))
            }
        }
    }
}
