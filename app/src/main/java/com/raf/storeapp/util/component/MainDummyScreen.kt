package com.raf.storeapp.util.component

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.raf.storeapp.R
import com.raf.storeapp.navigation.Routes

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainDummyScreen(
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    val listMainMenu = Routes.bottomBarItems

    val title = listMainMenu.find { it.route == currentRoute }?.label

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = title?.let { stringResource(it) } ?: stringResource(R.string.screen),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}