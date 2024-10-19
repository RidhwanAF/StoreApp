package com.raf.storeapp.home.util

import androidx.annotation.DrawableRes
import com.raf.storeapp.R

class Offer {
    val listOffer = listOf(
        ItemOffer(R.drawable.img_offer1, "Get 5%", "New User Offers"),
        ItemOffer(R.drawable.img_offer2, "Get 10%", "World Trash Day Offers"),
        ItemOffer(R.drawable.img_offer3, "Get 25%", "1st Anniversary Offers"),
    )

    data class ItemOffer(
        @DrawableRes val image: Int,
        val title: String,
        val desc: String,
    )
}