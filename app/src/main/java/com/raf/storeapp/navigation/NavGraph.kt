package com.raf.storeapp.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.raf.storeapp.home.HomeScreen
import com.raf.storeapp.store.StoreDetail
import com.raf.storeapp.util.component.MainDummyScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = Routes.MainScreen.Home.route,
            modifier = Modifier
                .fillMaxSize()
        ) {
            composable(Routes.MainScreen.Home.route) {
                HomeScreen(
                    navController = navController,
                    contentPadding = innerPadding,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }
            composable(Routes.Store.STORE_DETAIL_ROUTE.name) {
                StoreDetail(
                    navController = navController,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }
            composable(Routes.MainScreen.Explore.route) {
                MainDummyScreen(
                    navController = navController,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }
            composable(Routes.MainScreen.QrCode.route) {
                MainDummyScreen(
                    navController = navController,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }
            composable(Routes.MainScreen.Wallet.route) {
                MainDummyScreen(
                    navController = navController,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }
            composable(Routes.MainScreen.Profile.route) {
                MainDummyScreen(
                    navController = navController,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable
                )
            }
        }
    }
}