package com.example.todo.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todo.Task
import com.example.todo.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    viewModel: TaskViewModel,
    onTaskClick: (Task) -> Unit
) {
    // Coleta o estado das tarefas do ViewModel de forma segura
    val tasks by viewModel.tasks.collectAsStateWithLifecycle()

    // Estado local para o campo de texto da nova tarefa
    var newTaskTitle by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Minha Lista de Tarefas") },
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
            // Seção para Adicionar Tarefa
            TaskInputSection(
                newTaskTitle = newTaskTitle,
                onTitleChange = { newTaskTitle = it },
                onAddTask = {
                    viewModel.addTask(newTaskTitle)
                    newTaskTitle = "" // Limpa o campo após adicionar
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Seção da Lista de Tarefas
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items = tasks, key = { it.id }) { task ->
                    TaskItem(
                        task = task,
                        onCompletedChange = { viewModel.toggleTaskCompleted(task) },
                        onDelete = { viewModel.removeTask(task) },
                        onClick = { onTaskClick(task) }
                    )
                }
            }
        }
    }
}

@Composable
fun TaskInputSection(
    newTaskTitle: String,
    onTitleChange: (String) -> Unit,
    onAddTask: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = newTaskTitle,
            onValueChange = onTitleChange,
            label = { Text("Nova tarefa") },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = onAddTask,
            enabled = newTaskTitle.isNotBlank() // Botão só é clicável se houver texto
        ) {
            Text("Add")
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onCompletedChange: () -> Unit,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick), // Permite clicar no Card para ver detalhes
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Checkbox e Título
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = { onCompletedChange() }
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = task.title,
                    // Aplica estilo de "riscado" se a tarefa estiver concluída
                    style = TextStyle(
                        textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                    ),
                    color = if (task.isCompleted) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface
                )
            }

            // Botão de Excluir
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Excluir Tarefa",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}