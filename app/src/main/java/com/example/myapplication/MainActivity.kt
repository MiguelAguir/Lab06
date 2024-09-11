package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("profile") { ProfileScreen() }
        composable("settings") { SettingsScreen() }
        composable("favorites") { FavoritesScreen() }
        composable("trash") { TrashScreen() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val clickCount = remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CustomTopBar(navController)
        },
        bottomBar = {
            CustomBottomBar(navController)
        },
        floatingActionButton = {
            CustomFAB {
                clickCount.value += 1
            }
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Text(
                    text = "Button pressed ${clickCount.value} times",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(navController: NavHostController) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
            }
        },
        title = { Text(text = "Home") },
        actions = {
            IconButton(onClick = { navController.navigate("profile") }) {
                Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = null)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "User Profile") })
        },
        content = { innerPadding ->
            Text(
                text = "This is the Profile Screen",
                modifier = Modifier.padding(innerPadding)
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Settings") })
        },
        content = { innerPadding ->
            Text(
                text = "This is the Settings Screen",
                modifier = Modifier.padding(innerPadding)
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Favorites") })
        },
        content = { innerPadding ->
            Text(
                text = "This is the Favorites Screen",
                modifier = Modifier.padding(innerPadding)
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrashScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Trash") })
        },
        content = { innerPadding ->
            Text(
                text = "This is the Trash Screen",
                modifier = Modifier.padding(innerPadding)
            )
        }
    )
}

@Composable
fun CustomBottomBar(navController: NavHostController) {
    BottomAppBar(
        contentColor = Color.White,
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomIconButton(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                onClick = { navController.navigate("settings") }
            )
            CustomIconButton(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorites",
                onClick = { navController.navigate("favorites") }
            )
            CustomIconButton(
                imageVector = Icons.Default.Delete,
                contentDescription = "Trash",
                onClick = { navController.navigate("trash") }
            )
        }
    }
}

@Composable
fun CustomIconButton(
    imageVector: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
}

@Composable
fun CustomFAB(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() }
    ) {
        Text(
            fontSize = 24.sp,
            text = "+"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        NavGraph(navController)
    }
}