package com.raf.storeapp

import android.os.Bundle
import android.view.SoundEffectConstants
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.raf.storeapp.navigation.NavGraph
import com.raf.storeapp.navigation.Routes
import com.raf.storeapp.ui.theme.StoreAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            StoreAppTheme(
                dynamicColor = false,
                darkTheme = false
            ) {
                Scaffold(
                    bottomBar = {
                        BottomBarContent(
                            currentDestination = currentDestination,
                            navController = navController
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavGraph(navController = navController, innerPadding = innerPadding)
                }
            }
        }
    }
}

@Composable
fun BottomBarContent(
    currentDestination: NavDestination?,
    navController: NavController
) {
    val interactionSource = remember { MutableInteractionSource() }
    val view = LocalView.current

    val bottomBarMenu = Routes.bottomBarItems

    val showBottomBar by remember(currentDestination, navController) {
        derivedStateOf {
            val isShown = bottomBarMenu.any { it.route == currentDestination?.route }
            mutableStateOf(isShown)
        }
    }

    AnimatedVisibility(
        visible = showBottomBar.value,
        enter = slideInVertically { it },
        exit = slideOutVertically { it },
        modifier = Modifier
            .safeGesturesPadding()
            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(16.dp, CircleShape)
                .background(MaterialTheme.colorScheme.surface, CircleShape)
                .padding(horizontal = 8.dp)
        ) {
            bottomBarMenu.forEach { menu ->
                val selectedMenu = menu.route == currentDestination?.route
                val isQrScreen = menu.route == Routes.MainScreen.QrCode.route

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .size(80.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            navController.navigate(menu.route) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(Routes.MainScreen.Home.route) {
                                    saveState = true
                                }
                                view.playSoundEffect(SoundEffectConstants.CLICK)
                            }
                        }
                        .padding(vertical = 8.dp)
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    Icon(
                        painter = painterResource(
                            if (selectedMenu) menu.selectedIcon else menu.icon
                        ),
                        tint = if (selectedMenu && !isQrScreen) MaterialTheme.colorScheme.primary else Color.Unspecified,
                        contentDescription = stringResource(menu.label),
                        modifier = Modifier
                            .background(
                                color = if (isQrScreen) MaterialTheme.colorScheme.primary else Color.Transparent,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(if (isQrScreen) 8.dp else 0.dp)
                            .size(if (isQrScreen) 36.dp else 20.dp)
                    )
                    if (!isQrScreen) {
                        Text(
                            text = stringResource(menu.label),
                            fontWeight = if (selectedMenu) FontWeight.Normal else FontWeight.Light,
                            color = if (selectedMenu) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                            maxLines = 1,
                            fontSize = 12.sp,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}