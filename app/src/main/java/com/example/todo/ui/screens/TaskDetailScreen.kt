package com.example.todo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.todo.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    task: Task?,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes da Tarefa") },
                // Botão de navegação para voltar
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (task != null) {
                // Exibe os detalhes da tarefa
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))

                val status = if (task.isCompleted) "Concluída" else "Pendente"
                val statusColor = if (task.isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error

                Text(
                    text = "Status: $status",
                    style = MaterialTheme.typography.bodyLarge,
                    color = statusColor
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "ID da Tarefa: ${task.id}",
                    style = MaterialTheme.typography.bodySmall
                )

            } else {
                // Caso o ID da tarefa não seja encontrado
                Text(
                    text = "Tarefa não encontrada.",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}