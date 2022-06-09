package com.plcoding.composenavdestinationsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.plcoding.composenavdestinationsdemo.destinations.PostScreenDestination
import com.plcoding.composenavdestinationsdemo.destinations.ProfileScreenDestination
import com.plcoding.composenavdestinationsdemo.ui.theme.ComposeNavDestinationsDemoTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ComposeNavDestinationsDemoTheme {

                //add the NavHost call
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}


/*  val navController = rememberNavController()


//NAV HOST
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
  }*/


@Destination(start = true)
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator
) {
    Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login Screen")
        Button(onClick = {
            navigator.navigate(
                    ProfileScreenDestination(
                            user = User(
                                    name = "Tonnie",
                                    id = "13",
                                    created = LocalDateTime.now()
                            )
                    )
            )
        }) {
            Text("Go to Profile Screen")
        }
    }
}


@Destination
@Composable
fun ProfileScreen(
        //  navController = navController,
    navigator: DestinationsNavigator,
    user: User
) {


    /*val user = remember {
        User(
            name = name,
            id = userId,
            created = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(created), ZoneId.systemDefault()
            )
        )
    }*/
    Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Profile Screen: $user", textAlign = TextAlign.Center)


        Button(onClick = {

            // navController.navigate("post/true")

            //no arg passed
            navigator.navigate(PostScreenDestination())
        }) {
            Text("Go to Post Screen")
        }
    }
}

@Destination
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