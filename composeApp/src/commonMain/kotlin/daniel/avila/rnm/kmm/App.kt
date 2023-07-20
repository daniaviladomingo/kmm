package daniel.avila.rnm.kmm

import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.navigator.Navigator
import daniel.avila.rnm.kmm.theme.AppTheme
import daniel.avila.rnm.kmm.ui.features.characters.CharactersScreen

@OptIn(ExperimentalMaterial3Api::class, InternalVoyagerApi::class)
@Composable
internal fun App() = AppTheme {
    Navigator(CharactersScreen())
}

// @OptIn(ExperimentalMaterial3Api::class)
// @Composable
// internal fun App() = AppTheme {
//     var email by remember { mutableStateOf("") }
//     var password by remember { mutableStateOf("") }
//     var passwordVisibility by remember { mutableStateOf(false) }
//
//     Column(modifier = Modifier.fillMaxSize()) {
//
//         Text(
//             text = "Login",
//             style = MaterialTheme.typography.titleMedium,
//             modifier = Modifier.padding(16.dp)
//         )
//
//         OutlinedTextField(
//             value = email,
//             onValueChange = { email = it },
//             label = { Text("Email") },
//             singleLine = true,
//             modifier = Modifier.fillMaxWidth().padding(16.dp)
//         )
//
//         OutlinedTextField(
//             value = password,
//             onValueChange = { password = it },
//             label = { Text("Password") },
//             singleLine = true,
//             visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
//             modifier = Modifier.fillMaxWidth().padding(16.dp),
//             keyboardOptions = KeyboardOptions(
//                 keyboardType = KeyboardType.Password
//             ),
//             trailingIcon = {
//                 IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
//                     val imageVector = if (passwordVisibility) Icons.Default.Close else Icons.Default.Edit
//                     Icon(imageVector, contentDescription = if (passwordVisibility) "Hide password" else "Show password")
//                 }
//             }
//         )
//
//         Button(
//             onClick = { /* Handle login logic here */ },
//             modifier = Modifier.fillMaxWidth().padding(16.dp)
//         ) {
//             Text("Login")
//         }
//
//         TextButton(
//             onClick = { openUrl("https://github.com/terrakok") },
//             modifier = Modifier.fillMaxWidth().padding(16.dp)
//         ) {
//             Text("Open github")
//         }
//     }
// }
