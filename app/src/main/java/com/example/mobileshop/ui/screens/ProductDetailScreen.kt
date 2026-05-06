package com.example.mobileshop.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ImageNotSupported
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mobileshop.data.Shoe
import com.example.mobileshop.data.ShoeColor
import com.example.mobileshop.ui.util.formatUsd

@Composable
fun ProductDetailScreen(
    shoe: Shoe,
    cartTotal: Double,
    onBack: () -> Unit,
    onBagClick: () -> Unit,
    onAddToBag: (ShoeColor, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedColor by remember(shoe.id) { mutableStateOf(shoe.colors.first()) }
    var selectedSize by remember(shoe.id) { mutableStateOf(shoe.sizes.first()) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.White,
        bottomBar = {
            Surface(shadowElevation = 10.dp, tonalElevation = 2.dp) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column {
                        Text(
                            text = "Total",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                        Text(
                            text = shoe.price.formatUsd(),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                    Button(
                        onClick = { onAddToBag(selectedColor, selectedSize) },
                        modifier = Modifier
                            .height(48.dp)
                            .padding(start = 16.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = Color.White,
                        ),
                    ) {
                        Text(
                            text = "Add to Bag",
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }
        },
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 8.dp),
        ) {
            DetailHeader(
                cartTotal = cartTotal,
                onBack = onBack,
                onBagClick = onBagClick,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
            )

            DetailHeroImage(
                shoe = shoe,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
            )

            Spacer(Modifier.height(16.dp))

            Column(
                Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Text(
                    text = shoe.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )

                StarRow(rating = shoe.rating)

                Text(
                    text = shoe.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Select Color",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                )

                ColorRow(
                    colors = shoe.colors,
                    selected = selectedColor,
                    onSelect = { selectedColor = it },
                )

                Text(
                    text = "Size",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                )

                SizeRow(
                    sizes = shoe.sizes,
                    selected = selectedSize,
                    onSelect = { selectedSize = it },
                )

                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun DetailHeader(
    cartTotal: Double,
    onBack: () -> Unit,
    onBagClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(end = 8.dp)
                .clickable(onClick = onBagClick),
        ) {
            Icon(
                imageVector = Icons.Outlined.ShoppingBag,
                contentDescription = "Shopping bag",
                tint = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = cartTotal.formatUsd(),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@Composable
private fun DetailHeroImage(shoe: Shoe, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .aspectRatio(1.15f)
            .clip(RoundedCornerShape(22.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center,
    ) {
        if (shoe.imageRes != 0) {
            Image(
                painter = painterResource(id = shoe.imageRes),
                contentDescription = shoe.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        } else {
            Icon(
                imageVector = Icons.Outlined.ImageNotSupported,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline,
                modifier = Modifier.size(96.dp),
            )
        }
    }
}

@Composable
private fun StarRow(rating: Float, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        repeat(5) { index ->
            val threshold = index + 1f
            val icon = if (rating >= threshold) Icons.Filled.Star else Icons.Outlined.StarOutline
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (rating >= threshold) Color(0xFFFFC107) else MaterialTheme.colorScheme.outline,
                modifier = Modifier.size(18.dp),
            )
        }
        Text(
            text = "%.1f".format(rating),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 6.dp),
        )
    }
}

@Composable
private fun ColorRow(
    colors: List<ShoeColor>,
    selected: ShoeColor,
    onSelect: (ShoeColor) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        colors.forEach { color ->
            val selectedRing = selected.id == color.id
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .border(
                        width = if (selectedRing) 3.dp else 1.dp,
                        color = if (selectedRing) MaterialTheme.colorScheme.secondary else Color.LightGray,
                        shape = CircleShape,
                    )
                    .clickable { onSelect(color) },
                contentAlignment = Alignment.Center,
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(Color(color.argb)),
                )
            }
        }
    }
}

@Composable
private fun SizeRow(
    sizes: List<Int>,
    selected: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        sizes.forEach { size ->
            FilterChip(
                selected = selected == size,
                onClick = { onSelect(size) },
                label = { Text(text = size.toString()) },
            )
        }
    }
}
