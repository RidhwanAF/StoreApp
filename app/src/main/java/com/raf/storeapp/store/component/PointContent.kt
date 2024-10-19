package com.raf.storeapp.store.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raf.storeapp.R
import com.raf.storeapp.ui.theme.customOrangeColor

@Composable
fun PointContent(
    modifier: Modifier = Modifier,
    progressValue: Int,
    maxProgress: Int
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_point),
                contentDescription = stringResource(R.string.your_points),
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = progressValue.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = customOrangeColor
            )
        }
        val milestones = (0..maxProgress).step(500)

        Box(
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .background(MaterialTheme.colorScheme.onSurface.copy(0.25f), CircleShape)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction = progressValue / maxProgress.toFloat())
                        .height(10.dp)
                        .background(MaterialTheme.colorScheme.primary.copy(0.75f), CircleShape)
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .offset(y = 15.dp)
                    .fillMaxWidth()
            ) {
                milestones.forEach { milestone ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .background(
                                    if (milestone <= progressValue) MaterialTheme.colorScheme.primary
                                    else Color.Gray,
                                    CircleShape
                                )
                                .align(Alignment.End)
                        )
                        Text(
                            text = milestone.toString(),
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(0.75f)
                        )
                    }
                }
            }
        }
    }
}