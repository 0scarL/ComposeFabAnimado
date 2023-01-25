package com.example.composefabanimado

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun PantallaInicio() {

    var enable by rememberSaveable { mutableStateOf(false) }
    val rotacion: Float by animateFloatAsState(targetValue = if (enable) 45f else 0f)
    val scaleAnimation: Float by animateFloatAsState(targetValue = if (enable) 0.9f else 0f)

    Scaffold(Modifier.fillMaxSize()) {
        ConstraintLayout(Modifier.fillMaxSize()) {

            val (fabMiniSave, fabMiniExit, fabMiniFb, fab, fabB) = createRefs()

            Fab(onAbrirMenu = { enable = !enable }, rotacion,
                Modifier
                    .constrainAs(fab) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
                    .padding(end = 10.dp, bottom = 10.dp))

            FabMenuMini(enable = enable, scaleAnimation = scaleAnimation, icono = Icons.Filled.Save,
                Modifier
                    .constrainAs(fabMiniSave) {
                        bottom.linkTo(fab.top)
                        end.linkTo(fab.end)
                        start.linkTo(fab.start)
                    }
                    .padding(bottom = 8.dp, end = 10.dp))

            FabMenuMini(enable = enable,
                scaleAnimation = scaleAnimation,
                icono = Icons.Filled.ArrowBack,
                Modifier
                    .constrainAs(fabMiniExit) {
                        bottom.linkTo(fabMiniSave.top)
                        end.linkTo(fabMiniSave.end)
                        start.linkTo(fabMiniSave.start)
                    }
                    .padding(bottom = 8.dp, end = 10.dp))

            FabMenuMini(enable = enable,
                scaleAnimation = scaleAnimation,
                icono = Icons.Filled.Accessibility,
                Modifier
                    .constrainAs(fabMiniFb) {
                        bottom.linkTo(fabMiniExit.top)
                        end.linkTo(fabMiniExit.end)
                        start.linkTo(fabMiniExit.start)
                    }
                    .padding(bottom = 8.dp, end = 10.dp))

        }

    }

}

@Composable
fun Fab(onAbrirMenu: () -> Unit, rotacion: Float, modificador: Modifier) {
    FloatingActionButton(
        onClick = { onAbrirMenu() },
        backgroundColor = Color(0xFFFF9800),
        contentColor = Color.White,
        modifier = modificador,


        ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "add",
            modifier = Modifier.graphicsLayer(rotationZ = rotacion))
    }

}

@Composable
fun FabMenuMini(enable: Boolean, scaleAnimation: Float, icono: ImageVector, modificador: Modifier) {
    FloatingActionButton(onClick = { /*TODO*/ },
        backgroundColor = Color(0xFFFF9800),
        contentColor = Color.Transparent,
        modifier = modificador
            .size(45.dp)
            .graphicsLayer(scaleX = scaleAnimation, scaleY = scaleAnimation)
    ) {
        Icon(imageVector = icono, contentDescription = "save",
            tint = Color.White,
            modifier = Modifier)

    }

}


