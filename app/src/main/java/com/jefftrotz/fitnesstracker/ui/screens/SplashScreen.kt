package com.jefftrotz.fitnesstracker.ui.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.jefftrotz.fitnesstracker.R
import com.jefftrotz.fitnesstracker.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember { Animatable(initialValue = 0f) }

    LaunchedEffect(key1 = true, block = {
        scale.animateTo(targetValue = 0.9f, animationSpec = tween(durationMillis = 800, easing = {
            OvershootInterpolator(8f).getInterpolation(it)
        }))
        delay(timeMillis = 2000L)
        // TODO: Check if user is already logged in
        navController.navigate(Screens.LoginScreen.route) {
            popUpTo(Screens.SplashScreen.route) {
                inclusive = true
            }
        }
    })

    Surface(
        modifier = Modifier
            .padding(all = 16.dp)
            .size(360.dp)
            .scale(scale = scale.value),
        shape = CircleShape,
        border = BorderStroke(width = 2.dp, color = Color.LightGray)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_fitness_center_white),
            contentDescription = R.string.content_description_splash_screen_icon.toString(),
            modifier = Modifier.size(96.dp),
            contentScale = ContentScale.Fit
        )
    }
}