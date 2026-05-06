package com.example.mobileshop.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SportsSoccer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileshop.ui.theme.SplashGradientEnd
import com.example.mobileshop.ui.theme.SplashGradientMid
import com.example.mobileshop.ui.theme.SplashGradientStart

@Composable
fun SplashScreen(
    onGetStarted: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val gradient = Brush.verticalGradient(
        colors = listOf(SplashGradientStart, SplashGradientMid, SplashGradientEnd),
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(gradient)
            .padding(horizontal = 24.dp, vertical = 32.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Icon(
                    imageVector = Icons.Outlined.SportsSoccer,
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .graphicsLayer {
                            rotationZ = -12f
                            translationY = -12f
                        },
                    tint = Color.White.copy(alpha = 0.92f),
                )

                Text(
                    text = "LIVE YOUR PERFECT",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp,
                        lineHeight = 36.sp,
                    ),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )

                Text(
                    text = "Smart, gorgeous & fashionable collection makes you cool",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White.copy(alpha = 0.82f),
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp,
                )
            }

            Button(
                onClick = onGetStarted,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = SplashGradientEnd,
                ),
            ) {
                Text(
                    text = "Get Started",
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}
