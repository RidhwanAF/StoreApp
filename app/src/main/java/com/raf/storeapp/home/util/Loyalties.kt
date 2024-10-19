package com.raf.storeapp.home.util

import android.content.Context
import com.raf.storeapp.R
import com.raf.storeapp.util.component.ItemContentMenu

class Loyalties(context: Context) {
    val listMyLoyalties = listOf(
        ItemContentMenu(
            R.drawable.img_merchant_1,
            "Wooly Pig",
            "Dogpatch   223.3 mi",
            context.getString(R.string.view_discounts)
        ),
        ItemContentMenu(
            R.drawable.img_merchant_2,
            "Andina",
            "Hayes Vallery   223.3 mi",
            context.getString(R.string.view_discounts)
        ),
    )

    val listAllLoyalties = listOf(
        ItemContentMenu(
            R.drawable.img_loyalties_1,
            "Kuan Zhai Alley",
            "Dogpatch   223.3 mi",
            context.getString(R.string.view_discounts)
        ),
        ItemContentMenu(
            R.drawable.img_loyalties_2,
            "Hayz Dog",
            "Hayes Vallery   223.3 mi",
            context.getString(R.string.view_discounts)
        ),
        ItemContentMenu(
            R.drawable.img_loyalties_3,
            "The Happy Vegan",
            "Dogpatch   223.3 mi",
            context.getString(R.string.view_discounts)
        ),
        ItemContentMenu(
            R.drawable.img_loyalties_4,
            "Kahnfections",
            "Hayes Vallery   223.3 mi",
            context.getString(R.string.view_discounts)
        ),
        ItemContentMenu(
            R.drawable.img_loyalties_5,
            "Coach Sushi",
            "Dogpatch   223.3 mi",
            context.getString(R.string.view_discounts)
        ),
        ItemContentMenu(
            R.drawable.img_loyalties_6,
            "New York Pizza",
            "Hayes Vallery   223.3 mi",
            context.getString(R.string.view_discounts)
        ),
        ItemContentMenu(
            R.drawable.img_loyalties_7,
            "Su-Dam Korean",
            "Dogpatch   223.3 mi",
            context.getString(R.string.view_discounts)
        ),
        ItemContentMenu(
            R.drawable.img_merchant_2,
            "Andina",
            "Hayes Vallery   223.3 mi",
            context.getString(R.string.view_discounts)
        ),
    )
}