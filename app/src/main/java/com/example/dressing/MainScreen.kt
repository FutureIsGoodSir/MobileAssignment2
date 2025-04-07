package com.example.dressing

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen() {
    val clothes = listOf(
        "arms",
        "ears",
        "eyebrows",
        "eyes",
        "glasses",
        "hat",
        "mouth",
        "mustache",
        "nose",
        "shoes"
    )

    val drawableId = IntArray(10)
    for (index in drawableId.indices) {
        drawableId[index] = when (clothes[index]) {
            "arms" -> R.drawable.arms
            "ears" -> R.drawable.ears
            "eyebrows" -> R.drawable.eyebrows
            "eyes" -> R.drawable.eyes
            "glasses" -> R.drawable.glasses
            "hat" -> R.drawable.hat
            "mouth" -> R.drawable.mouth
            "mustache" -> R.drawable.mustache
            "nose" -> R.drawable.nose
            "shoes" -> R.drawable.shoes
            else -> 0
        }
    }

    val checkSaver = listSaver<SnapshotStateList<Boolean>, Boolean>(
        save = {
            it
        },
        restore = {
            it.toMutableStateList()
        }
    )
    val checkStates = rememberSaveable(saver = checkSaver) {
        MutableList<Boolean>(10) { false }.toMutableStateList()
    }

    val orientation = LocalConfiguration.current.orientation;
    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        Column {
            Box(modifier = Modifier.weight(1f)) {
                Image(
                    painterResource(R.drawable.body),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                )
                checkStates.forEachIndexed { index, item ->
                    if (item) {
                        Image(
                            painterResource(drawableId[index]),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                for (i in 0 until 5) {
                    Row(horizontalArrangement = Arrangement.SpaceAround) {
                        Row(
                            Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Spacer(Modifier.width(30.dp))
                            Checkbox(
                                checked = checkStates[2 * i],
                                onCheckedChange = { checkStates[2 * i] = it },
                            )
                            Text(clothes[2 * i]+"202111373정일혁")
                        }
                        Row(
                            Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(30.dp))
                            Checkbox(
                                checked = checkStates[2 * i + 1],
                                onCheckedChange = { checkStates[2 * i + 1] = it },
                            )
                            Text(clothes[2 * i + 1])
                        }
                    }
                }
            }
        }
    } else {
        Row {
            Box() {
                Image(
                    painterResource(R.drawable.body),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxHeight()
                )
                checkStates.forEachIndexed { index, item ->
                    if (item) {
                        Image(
                            painterResource(drawableId[index]),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxHeight()
                        )
                    }
                }
            }
            Column(
                Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                for (i in 0 until 5) {
                    Row(horizontalArrangement = Arrangement.SpaceAround) {
                        Row(
                            Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = checkStates[2 * i],
                                onCheckedChange = { checkStates[2 * i] = it },
                            )
                            Text(clothes[2 * i])
                        }
                        Row(
                            Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = checkStates[2 * i + 1],
                                onCheckedChange = { checkStates[2 * i + 1] = it },
                            )
                            Text(clothes[2 * i + 1])
                        }
                    }
                }
            }
        }
    }
}


@Preview
    (widthDp = 800, heightDp = 400, showBackground = true)
@Composable
private fun MainScreenPreview() {
    MainScreen()
}