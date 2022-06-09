package com.plcoding.composenavdestinationsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.plcoding.composenavdestinationsdemo.ui.theme.ComposeNavDestinationsDemoTheme
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {

            ComposeNavDestinationsDemoTheme {


                val navController = rememberNavController()


                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {

                    //1ST SCREEN COMPOSABLE
                    composable("login") {


                        LoginScreen(navController)
                    }


                    //2ND SCREEN COMPOSABLE
                    composable(
                        route = "profile/{name}/{userId}/{timestamp}",

                            //argument 1
                        arguments = listOf(
                            navArgument("name") {
                                type = NavType.StringType
                            },

                                //argument 2
                            navArgument("userId") {
                                type = NavType.StringType
                            },


                                //argument 3
                            navArgument("timestamp") {
                                type = NavType.LongType
                            },
                        )
                    ) {

                        //Extracting NavArguments from NavBackStackEntry
                        val name = it.arguments?.getString("name")!!
                        val userId = it.arguments?.getString("userId")!!
                        val timestamp = it.arguments?.getLong("timestamp")!!

                        ProfileScreen(
                            navController = navController,
                            name = name,
                            userId = userId,
                            created = timestamp
                        )
                    }

                    //3RD SCREEN COMPOSABLE
                    composable("post/{showOnlyPostsByUser}", arguments = listOf(

                            //argument 1
                        navArgument("showOnlyPostsByUser") {
                            type = NavType.BoolType
                            defaultValue = false
                        }
                    )) {
                        val showOnlyPostsByUser =
                            it.arguments?.getBoolean("showOnlyPostsByUser") ?: false
                        PostScreen(showOnlyPostsByUser)
                    }
                }
            }
        }
    }
}

@Composable
fun LoginScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login Screen")
        Button(onClick = {
            navController.navigate("profile/philipp/userid/123456789")
        }) {
            Text("Go to Profile Screen")
        }
    }
}

@Composable
fun ProfileScreen(
    navController: NavController,
    name: String,
    userId: String,
    created: Long
) {
    val user = remember {
        User(
            name = name,
            id = userId,
            created = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(created), ZoneId.systemDefault()
            )
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Profile Screen: $user", textAlign = TextAlign.Center)
        Button(onClick = {
            navController.navigate("post/true")
        }) {
            Text("Go to Post Screen")
        }
    }
}

@Composable
fun PostScreen(
    showOnlyPostsByUser: Boolean = false
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Post Screen, $showOnlyPostsByUser")
    }
}