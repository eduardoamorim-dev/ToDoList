package com.example.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todo.ui.screens.TaskDetailScreen
import com.example.todo.ui.screens.TaskListScreen
import com.example.todo.ui.theme.ToDoTheme // Importe seu tema

class MainActivity : ComponentActivity() {

    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Aplicando o tema padrão do Material 3
            ToDoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigator(viewModel = taskViewModel)
                }
            }
        }
    }
}

/**
 * Define as rotas de navegação do aplicativo.
 */
object Routes {
    const val TASK_LIST = "taskList"
    // Define um argumento {taskId} na rota de detalhes
    const val TASK_DETAIL = "taskDetail/{taskId}"

    // Função auxiliar para construir a rota de detalhes com um ID
    fun taskDetailRoute(taskId: Int) = "taskDetail/$taskId"
}

/**
 * Composable principal que gerencia a navegação (NavHost).
 */
@Composable
fun AppNavigator(viewModel: TaskViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.TASK_LIST) {

        // Rota para a Tela de Lista
        composable(Routes.TASK_LIST) {
            TaskListScreen(
                viewModel = viewModel,
                // Ação de clique que navega para os detalhes da tarefa
                onTaskClick = { task ->
                    navController.navigate(Routes.taskDetailRoute(task.id))
                }
            )
        }

        // Rota para a Tela de Detalhes
        composable(
            route = Routes.TASK_DETAIL,
            // Define o tipo do argumento esperado (Int)
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            // Extrai o ID da tarefa dos argumentos da rota
            val taskId = backStackEntry.arguments?.getInt("taskId")

            // Busca a tarefa no ViewModel
            val task = taskId?.let { viewModel.getTaskById(it) }

            TaskDetailScreen(
                task = task,
                // Ação para voltar à tela anterior
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}