package com.ad_coding.noteappcourse.window
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.WindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun rememberWindow(): infoWindow
{
  //Obtener la configuracion actual del dsipositivo
    val configuration = LocalConfiguration.current
    //Determinamos el tipo de pantalla

    return infoWindow(
        windowWidthInfo = when{
            configuration.screenWidthDp < 600 -> infoWindow.tipoPantalla.Compact
            configuration.screenWidthDp < 840 -> infoWindow.tipoPantalla.Medium
            else -> infoWindow.tipoPantalla.Expanded
        },
        //Determinamos el tipo de ventana en funcion de la altura
        windowHeightInfo = when {
            configuration.screenHeightDp < 400 -> infoWindow.tipoPantalla.Compact
            configuration.screenHeightDp < 900 -> infoWindow.tipoPantalla.Medium
            else -> infoWindow.tipoPantalla.Expanded
        },
        screenWith = configuration.screenWidthDp.dp,
        screenHeight = configuration.screenHeightDp.dp
    )
}

//Clase que nos ayudara a detectar el tipo de pantalla
data class infoWindow(
    val windowWidthInfo: tipoPantalla, // Tipo de pantalla en funcion del ancho
    val windowHeightInfo: tipoPantalla, // Tipo de pantalla en funcion de la altura
    val screenWith: Dp, // Ancho de pantalla en unidades densidad independientes
    val screenHeight: Dp // Alto de pantalla en unidades densidad independientes

){
    sealed class tipoPantalla{
        object Compact: tipoPantalla() //Ventanas pequeñas
        object Medium: tipoPantalla()//Pantallas medianas
        object Expanded: tipoPantalla() //Pantallas grandes
    }
}
