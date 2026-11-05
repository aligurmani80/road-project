package com.example.road.ui.onboarding

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.road.R
import com.example.road.ui.theme.BackgroundDark
import com.example.road.ui.theme.Hairline
import com.example.road.ui.theme.MutedText
import com.example.road.ui.theme.PrimaryWhite
import com.example.road.ui.theme.RoadAssistOrange
import com.example.road.ui.theme.RoadTheme
import com.example.road.ui.theme.SecondaryText

private val HorizontalScreenPadding = 24.dp

@Composable
fun OnboardingScreen(
    onGetStarted: () -> Unit,
    onSkip: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val isPreview = LocalInspectionMode.current
    var contentVisible by remember { mutableStateOf(isPreview) }
    LaunchedEffect(Unit) { contentVisible = true }

    val heroAlpha by animateFloatAsState(
        targetValue = if (contentVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 520, delayMillis = 80, easing = FastOutSlowInEasing),
        label = "heroAlpha",
    )
    val ctaAlpha by animateFloatAsState(
        targetValue = if (contentVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 480, delayMillis = 240, easing = FastOutSlowInEasing),
        label = "ctaAlpha",
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDark),
    ) {
        Image(
            painter = painterResource(R.drawable.roadassist_onboarding_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        OnboardingScrims()

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing),
        ) {
            val compactHeight = maxHeight < 700.dp
            val headlineSize = when {
                maxWidth < 372.dp -> 33.sp
                maxWidth < 412.dp -> 36.sp
                else -> 38.sp
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = HorizontalScreenPadding),
            ) {
                Spacer(Modifier.height(if (compactHeight) 8.dp else 12.dp))
                RoadAssistHeader(onSkip = onSkip)
                Spacer(Modifier.height(if (compactHeight) 30.dp else 46.dp))
                OnboardingHeroContent(
                    headlineSize = headlineSize,
                    modifier = Modifier.graphicsLayer {
                        alpha = heroAlpha
                        translationY = (1f - heroAlpha) * 10.dp.toPx()
                    },
                )
                Spacer(Modifier.height(20.dp))
                Spacer(Modifier.weight(1f))
                OnboardingBenefits()
                Spacer(Modifier.height(if (compactHeight) 18.dp else 26.dp))
                GetStartedButton(
                    onClick = onGetStarted,
                    modifier = Modifier.graphicsLayer {
                        alpha = ctaAlpha
                        translationY = (1f - ctaAlpha) * 10.dp.toPx()
                    },
                )
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}

@Composable
private fun OnboardingScrims() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    0.00f to Color.Black.copy(alpha = 0.68f),
                    0.16f to Color.Black.copy(alpha = 0.38f),
                    0.34f to Color.Black.copy(alpha = 0.10f),
                    0.55f to Color.Transparent,
                    0.70f to Color.Black.copy(alpha = 0.26f),
                    0.84f to Color.Black.copy(alpha = 0.62f),
                    1.00f to Color.Black.copy(alpha = 0.90f),
                ),
            ),
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.horizontalGradient(
                    0.00f to Color.Black.copy(alpha = 0.30f),
                    0.45f to Color.Black.copy(alpha = 0.10f),
                    0.75f to Color.Transparent,
                    1.00f to Color.Transparent,
                ),
            ),
    )
}

@Composable
fun RoadAssistHeader(
    onSkip: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.align(Alignment.TopStart),
            verticalAlignment = Alignment.Top,
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_roadassist_mark),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(width = 42.dp, height = 36.dp),
            )
            Spacer(Modifier.width(9.dp))
            Column {
                val wordmark = buildAnnotatedString {
                    withStyle(SpanStyle(color = PrimaryWhite)) {
                        append(stringResource(R.string.brand_name_road))
                    }
                    withStyle(SpanStyle(color = RoadAssistOrange)) {
                        append(stringResource(R.string.brand_name_assist))
                    }
                }
                Text(
                    text = wordmark,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    softWrap = false,
                )
                Text(
                    text = stringResource(R.string.brand_tagline),
                    style = MaterialTheme.typography.labelSmall,
                    color = MutedText,
                    maxLines = 1,
                    softWrap = false,
                    modifier = Modifier.padding(top = 1.dp),
                )
            }
        }

        SkipButton(
            onClick = onSkip,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 3.dp),
        )
    }
}

@Composable
private fun SkipButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .minimumInteractiveComponentSize()
            .clip(CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = PrimaryWhite.copy(alpha = 0.22f)),
                role = Role.Button,
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .height(36.dp)
                .background(Color.Black.copy(alpha = 0.30f), CircleShape)
                .border(1.dp, Hairline, CircleShape)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.onboarding_skip),
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.sp),
                color = PrimaryWhite.copy(alpha = 0.92f),
            )
            Spacer(Modifier.width(6.dp))
            Icon(
                painter = painterResource(R.drawable.ic_chevron_right),
                contentDescription = null,
                tint = PrimaryWhite.copy(alpha = 0.9f),
                modifier = Modifier.size(13.dp),
            )
        }
    }
}

@Composable
fun OnboardingHeroContent(
    headlineSize: TextUnit,
    modifier: Modifier = Modifier,
    headlineStyle: TextStyle = MaterialTheme.typography.headlineLarge,
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .width(26.dp)
                .height(3.dp)
                .background(RoadAssistOrange, RoundedCornerShape(1.5.dp)),
        )
        Spacer(Modifier.height(14.dp))

        val heading = buildAnnotatedString {
            withStyle(SpanStyle(color = PrimaryWhite)) {
                append(stringResource(R.string.onboarding_headline_primary))
            }
            append('\n')
            withStyle(SpanStyle(color = RoadAssistOrange)) {
                append(stringResource(R.string.onboarding_headline_accent))
            }
        }
        Text(
            text = heading,
            style = headlineStyle.copy(
                fontSize = headlineSize,
                lineHeight = (headlineSize.value * 1.16f).sp,
            ),
            textAlign = TextAlign.Start,
        )

        Spacer(Modifier.height(14.dp))
        Text(
            text = stringResource(R.string.onboarding_subtitle),
            style = MaterialTheme.typography.bodyMedium,
            color = SecondaryText,
            textAlign = TextAlign.Start,
            modifier = Modifier.widthIn(max = 232.dp),
        )
    }
}

@Composable
fun OnboardingBenefits(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top,
    ) {
        BenefitItem(
            iconRes = R.drawable.ic_benefit_shield_check,
            label = stringResource(R.string.onboarding_benefit_assistance),
            modifier = Modifier.weight(1f),
        )
        BenefitItem(
            iconRes = R.drawable.ic_benefit_map_pin,
            label = stringResource(R.string.onboarding_benefit_coverage),
            modifier = Modifier.weight(1f),
        )
        BenefitItem(
            iconRes = R.drawable.ic_benefit_users,
            label = stringResource(R.string.onboarding_benefit_drivers),
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
fun BenefitItem(
    iconRes: Int,
    label: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.semantics(mergeDescendants = true) {},
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(46.dp)
                .background(Color.Black.copy(alpha = 0.22f), CircleShape)
                .border(1.dp, RoadAssistOrange.copy(alpha = 0.55f), CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                tint = RoadAssistOrange,
                modifier = Modifier.size(22.dp),
            )
        }
        Spacer(Modifier.height(10.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = PrimaryWhite.copy(alpha = 0.90f),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun GetStartedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.985f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMedium,
        ),
        label = "ctaScale",
    )

    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .shadow(
                elevation = 16.dp,
                shape = CircleShape,
                clip = false,
                ambientColor = RoadAssistOrange.copy(alpha = 0.35f),
                spotColor = RoadAssistOrange.copy(alpha = 0.45f),
            ),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = RoadAssistOrange,
            contentColor = PrimaryWhite,
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
        ),
        interactionSource = interactionSource,
        contentPadding = PaddingValues(0.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.onboarding_get_started),
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(Modifier.width(10.dp))
            Icon(
                painter = painterResource(R.drawable.ic_chevron_right),
                contentDescription = null,
                tint = PrimaryWhite,
                modifier = Modifier.size(17.dp),
            )
        }
    }
}

@Preview(name = "Onboarding 390x844", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun OnboardingScreenPreview() {
    RoadTheme {
        OnboardingScreen(onGetStarted = {}, onSkip = {})
    }
}

@Preview(name = "Onboarding small 360x640", showBackground = true, widthDp = 360, heightDp = 640)
@Composable
private fun OnboardingScreenSmallPreview() {
    RoadTheme {
        OnboardingScreen(onGetStarted = {}, onSkip = {})
    }
}
