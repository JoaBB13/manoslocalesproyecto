package com.undef.manoslocalesproyecto
// Para navegar entre activitys, usamos intentn y lanzamos la activity agregada en el manifest.
//Aprovechando que android guarda cada activity en una pila, cuando presionamos atras, la pantalla anterior vuelve automaticamente
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.undef.manoslocalesproyecto.R
import android.content.Intent
import androidx.compose.ui.platform.LocalContext

@Preview(showBackground = true, showSystemUi = true)
@Composable

fun LoginScreen() {
    var email by remember { mutableStateOf("") }//almacenan lo que el usario escribe, remember:guarda el estado duranterecomoposiciones(rotar pantalla)
    var password by remember { mutableStateOf("") }//mustableStateOf:Hace que compose redibuje la UI cuando cambian estos valores

    Column(//organiza los elementos verticalmente
        modifier = Modifier
            .fillMaxSize()//ocupa toda la pantalla
            .padding(24.dp),//con margenes de 24dp
        verticalArrangement = Arrangement.Center,//centra los elementos verticalmente
        horizontalAlignment = Alignment.CenterHorizontally//centra los lementos horizontalmente
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo de la app",//texto para lectores de pantalla
            modifier = Modifier
                .size(140.dp)
                .padding(bottom = 24.dp)
        )

        OutlinedTextField(//campo de texto
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            singleLine = true,//evita saltos de linea
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),//muestra la contraseña como puntos
            modifier = Modifier.fillMaxWidth(),
            //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)//muestra teclado optimizado para contraseñas
        )

        Spacer(modifier = Modifier.height(24.dp))

        val context = LocalContext.current//obtienen el contexto de android para iniciar actividades

        Button(
            onClick = {
                // Aquí se van avalidar credenciales cuando se haga login real

                // Navegar a ProductActivity
                val intent = Intent(context, ProductActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()//boton ocupa todo el ancho disponible
        ) {
            Text("Ingresar")
        }

        TextButton(//boton sin fondo
            onClick = {
                //Genereamos intent para navegar a register activity
                val intent = Intent(context, RegisterActivity::class.java)
                context.startActivity(intent)
            }
        ) {
            Text("¿No tenés cuenta? Registrate aqui")
        }

        TextButton(
            onClick = {
                //Generamos intent para navegar a passwortd activity
                val intent = Intent(context, PasswordActivity::class.java)
                context.startActivity(intent)
            }
        ) {
            Text("¿Olvidaste tu contraseña? Cambiala aqui")
        }
    }
}