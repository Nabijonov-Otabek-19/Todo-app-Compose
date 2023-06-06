package uz.gita.todoappexam

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import uz.gita.todoappexam.navigation.NavigationHandler
import uz.gita.todoappexam.ui.screen.home.HomeScreen
import uz.gita.todoappexam.ui.theme.TodoAppTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationHandler: NavigationHandler

    private val myPermissioneRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        myPermissioneRequest.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }

                    Navigator(screen = HomeScreen()) { navigator ->
                        LaunchedEffect(key1 = navigator) {
                            navigationHandler.navigationBuffer.onEach {
                                it(navigator)
                            }.collect()
                        }
                        CurrentScreen()
                    }
                }
            }
        }
    }
}