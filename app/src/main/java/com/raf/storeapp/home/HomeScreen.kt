package com.raf.storeapp.home

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.raf.storeapp.R
import com.raf.storeapp.home.component.EarnShopPassPoint
import com.raf.storeapp.home.component.HomeCardHeader
import com.raf.storeapp.home.component.ItemEarnShopPassPoint
import com.raf.storeapp.home.component.ItemFollowingMerchant
import com.raf.storeapp.home.component.ItemFollowingMerchantCard
import com.raf.storeapp.home.component.ItemMenu
import com.raf.storeapp.home.component.ItemMenuCard
import com.raf.storeapp.home.component.ItemOffer
import com.raf.storeapp.home.component.ReferFriendCard
import com.raf.storeapp.home.util.Loyalties
import com.raf.storeapp.home.util.Offer
import com.raf.storeapp.home.util.PopularNearYou
import com.raf.storeapp.navigation.Routes
import com.raf.storeapp.ui.theme.customBlueColor
import com.raf.storeapp.util.component.ItemContentCard
import com.raf.storeapp.util.component.TitleHeader

@OptIn(
    ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class
)
@Composable
fun HomeScreen(
    navController: NavController,
    contentPadding: PaddingValues,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    // Data
    val listItemMenu = listOf(
        ItemMenu(
            R.drawable.img_bundles,
            stringResource(R.string.bundles),
            stringResource(R.string.get_bundles)
        ),
        ItemMenu(
            R.drawable.img_coupons,
            stringResource(R.string.coupons),
            stringResource(R.string.redeem_coupons)
        ),
    )
    val listPopularNearYou = PopularNearYou().listPopularNearYou
    val listMyLoyalties = Loyalties(context).listMyLoyalties
    val listAllLoyalties = Loyalties(context).listAllLoyalties
    val listOffers = Offer().listOffer
    val listEarnShopPassPoint = listOf(
        EarnShopPassPoint(
            R.drawable.img_earn_pass_point_1,
            stringResource(R.string.explore),
            "Browse the activities on the Shop Pass"
        ),
        EarnShopPassPoint(
            R.drawable.img_earn_pass_point_2,
            stringResource(R.string.refer_a_friend),
            "Browse the activities on the Shop Pass"
        ),
    )
    val listFollowingMerchant = listOf(
        ItemFollowingMerchant(R.drawable.img_merchant_1, "Wooly Pig"),
        ItemFollowingMerchant(R.drawable.img_merchant_2, "Andina"),
        ItemFollowingMerchant(R.drawable.img_merchant_3, "THV"),
        ItemFollowingMerchant(R.drawable.img_merchant_2, "Andina"),
    )

    // User Input
    var searchQuery by rememberSaveable { mutableStateOf("") }

    // Action
    val onNavigateToStoreDetail = {
        navController.navigate(Routes.Store.STORE_DETAIL_ROUTE.name) {
            launchSingleTop = true
        }
    }
    val onExploreMap = {
        val gmmIntentUri = Uri.parse("geo:0,0?q=store")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")

        try {
            context.startActivity(mapIntent)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "No map application found", Toast.LENGTH_SHORT).show()
        }
    }

    with(sharedTransitionScope) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.shop_pass),
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    actions = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            List(2) {
                                FilledIconButton(
                                    onClick = { /*NO-OP*/ },
                                    colors = IconButtonDefaults.filledIconButtonColors(
                                        containerColor = MaterialTheme.colorScheme.onPrimary,
                                        contentColor = MaterialTheme.colorScheme.primary
                                    ),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(
                                            if (it == 0) R.drawable.ic_chat
                                            else R.drawable.ic_notification
                                        ),
                                        contentDescription = "action Button",
                                        modifier = Modifier
                                            .size(24.dp)
                                            .padding(2.dp)
                                    )
                                }
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        scrolledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.75f),
                    ),
                    scrollBehavior = scrollBehavior
                )
            },
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Box(
                    contentAlignment = Alignment.TopCenter,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Box(modifier = Modifier.padding(innerPadding))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(275.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.img_header1),
                            contentDescription = "header",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.TopCenter)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.75f))
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 110.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            TextField(
                                value = searchQuery,
                                onValueChange = { searchQuery = it },
                                leadingIcon = {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_search),
                                        contentDescription = "Search",
                                        modifier = Modifier.size(16.dp)
                                    )
                                },
                                placeholder = {
                                    Text(text = "Search")
                                },
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    imeAction = ImeAction.Search
                                ),
                                keyboardActions = KeyboardActions(
                                    onSearch = {
                                        focusManager.clearFocus()
                                    }
                                ),
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                shape = CircleShape,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .weight(1f)
                            )
                            FilledIconButton(
                                onClick = { /*NO-OP*/ },
                                colors = IconButtonDefaults.filledIconButtonColors(
                                    containerColor = MaterialTheme.colorScheme.onPrimary,
                                    contentColor = MaterialTheme.colorScheme.primary
                                )
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_filter),
                                    contentDescription = "filter",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                        HomeCardHeader(
                            onClicked = { onNavigateToStoreDetail() },
                            modifier = Modifier
                                .sharedElement(
                                    sharedTransitionScope.rememberSharedContentState(key = "header"),
                                    animatedVisibilityScope = animatedContentScope,
                                )
                        )
                    }
                }
                ReferFriendCard(onClicked = { onNavigateToStoreDetail() })
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        16.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    listItemMenu.forEach { menu ->
                        ItemMenuCard(
                            modifier = Modifier.weight(1f),
                            menu = menu,
                            onClicked = { onNavigateToStoreDetail() })
                    }
                }
                // Popular near you
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    TitleHeader(
                        title = stringResource(R.string.popular_near_you),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(listPopularNearYou) { menu ->
                            ItemContentCard(
                                modifier = Modifier.width(140.dp),
                                menu = menu,
                                onClicked = { onNavigateToStoreDetail() }
                            )
                        }
                    }
                }
                // List My Loyalties
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                ) {
                    TitleHeader(
                        title = stringResource(R.string.my_loyalties),
                        modifier = Modifier.padding(horizontal = 16.dp),
                        onClicked = { onNavigateToStoreDetail() }
                    )
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(listMyLoyalties) { menu ->
                            ItemContentCard(
                                menu = menu,
                                isWide = true,
                                onClicked = { onNavigateToStoreDetail() }
                            )
                        }
                    }
                }
                // All Loyalties
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                ) {
                    TitleHeader(
                        title = stringResource(R.string.all_loyalties),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(
                            16.dp,
                            Alignment.CenterHorizontally
                        ),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        listAllLoyalties.forEach { menu ->
                            ItemContentCard(
                                menu = menu,
                                onClicked = { onNavigateToStoreDetail() }
                            )
                        }
                    }
                    TextButton(
                        onClick = { onNavigateToStoreDetail() },
                        colors = ButtonDefaults.textButtonColors(contentColor = customBlueColor),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = stringResource(R.string.see_all_royalties))
                    }
                }
                // Offers
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                ) {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(listOffers) { offer ->
                            ItemOffer(
                                offer = offer,
                                onClicked = { onNavigateToStoreDetail() }
                            )
                        }
                    }
                }
                // How to Earn Shop Pass Point
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                ) {
                    TitleHeader(
                        title = stringResource(R.string.how_to_earn_shop_pass_point),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(listEarnShopPassPoint) { data ->
                            ItemEarnShopPassPoint(
                                data = data,
                                onClicked = { onNavigateToStoreDetail() }
                            )
                        }
                    }
                }
                // Explore Nearby
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                ) {
                    TitleHeader(
                        title = stringResource(R.string.explore_nearby),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(24.dp))
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(horizontal = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.img_map),
                            contentDescription = stringResource(R.string.explore_nearby),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(24.dp))
                        )
                        ElevatedButton(
                            onClick = { onExploreMap() },
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = MaterialTheme.colorScheme.onPrimary,
                                contentColor = MaterialTheme.colorScheme.primary
                            ),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.BottomCenter)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_location),
                                    contentDescription = stringResource(R.string.explore_nearby),
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = stringResource(R.string.explore_map),
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
                // Following Merchant
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    TitleHeader(
                        title = stringResource(R.string.following_merchant),
                        modifier = Modifier.padding(horizontal = 16.dp),
                        onClicked = { onNavigateToStoreDetail() }
                    )
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(listFollowingMerchant) { menu ->
                            ItemFollowingMerchantCard(
                                menu = menu,
                                onClicked = { onNavigateToStoreDetail() }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(contentPadding.calculateBottomPadding()))
            }
        }
    }
}