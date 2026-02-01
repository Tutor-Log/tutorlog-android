package com.tutorlog.app.feature.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tutorlog.app.design.LocalColors
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.generated.destinations.LoginScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination<RootGraph>(start = true)
@Composable
fun LandingScreen(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    viewModel: LandingViewModel = hiltViewModel()
) {

    viewModel.collectSideEffect() {
        when(it) {
            is LandingSideEffect.NavigateToLoginScreen -> {
                navigator.navigate(LoginScreenDestination)
            }
            is LandingSideEffect.NavigateToHomeScreen -> {
                navigator.navigate(HomeScreenDestination)
            }
        }
    }
    ViolinAppLoadingScreen()
}

@Composable
fun ViolinAppLoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LocalColors.BackgroundDefaultDark)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(384.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            LocalColors.Gray800.copy(alpha = 0.3f),
                            Color.Transparent
                        )
                    )
                )
                .align(Alignment.TopCenter)
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo Container
            Surface(
                modifier = Modifier
                    .size(96.dp)
                    .shadow(elevation = 24.dp, spotColor = Color.Black.copy(alpha = 0.5f)),
                shape = RoundedCornerShape(24.dp),
                color = LocalColors.Gray800,
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    LocalColors.Gray700.copy(alpha = 0.5f)
                )
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Outlined.PlayArrow,
                        contentDescription = "Music Logo",
                        tint = LocalColors.PrimaryGreen,
                        modifier = Modifier.size(80.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            CircularProgressIndicator(
                modifier = Modifier.size(48.dp),
                color = LocalColors.PrimaryGreen,
                strokeWidth = 4.dp,
                trackColor = LocalColors.Gray800
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Fetching your data...",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Text(
                text = "Please wait while we sync your\nlatest classes and fees.",
                color = LocalColors.Gray400,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp
            )
        }
    }
}

@Preview
@Composable
fun PreviewViolinAppLoading() {
    MaterialTheme {
        ViolinAppLoadingScreen()
    }
}