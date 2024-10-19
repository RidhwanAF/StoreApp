package com.raf.storeapp.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.raf.storeapp.R

open class Routes {

    enum class Store {
        STORE_DETAIL_ROUTE
    }

    enum class Main {
        HOME_ROUTE,
        EXPLORE_ROUTE,
        QR_CDE_ROUTE,
        WALLET_ROUTE,
        PROFILE_ROUTE
    }

    sealed class MainScreen(
        val route: String,
        @StringRes val label: Int,
        @DrawableRes val icon: Int,
        @DrawableRes val selectedIcon: Int,
    ) {
        data object Home : MainScreen(
            Main.HOME_ROUTE.name,
            R.string.home,
            R.drawable.ic_home,
            R.drawable.ic_home_active,
        )

        data object Explore : MainScreen(
            Main.EXPLORE_ROUTE.name,
            R.string.explore,
            R.drawable.ic_explore,
            R.drawable.ic_explore_active,
        )

        data object QrCode : MainScreen(
            Main.QR_CDE_ROUTE.name,
            R.string.scan,
            R.drawable.ic_qr_code_menu,
            R.drawable.ic_qr_code_menu,
        )

        data object Wallet : MainScreen(
            Main.WALLET_ROUTE.name,
            R.string.wallet,
            R.drawable.ic_wallet,
            R.drawable.ic_wallet_active,
        )

        data object Profile : MainScreen(
            Main.PROFILE_ROUTE.name,
            R.string.profile,
            R.drawable.ic_profile,
            R.drawable.ic_profile_active,
        )
    }

    companion object {
        val bottomBarItems = listOf(
            MainScreen.Home,
            MainScreen.Explore,
            MainScreen.QrCode,
            MainScreen.Wallet,
            MainScreen.Profile
        )
    }
}