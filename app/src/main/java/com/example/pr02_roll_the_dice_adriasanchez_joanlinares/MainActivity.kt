package com.example.pr02_roll_the_dice_adriasanchez_joanlinares

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pr02_roll_the_dice_adriasanchez_joanlinares.ui.theme.Pr02rollthediceadriasanchez_joanlinaresTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pr02rollthediceadriasanchez_joanlinaresTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ){
                  DiceGame()
                }
            }
        }
    }
}

@Composable
fun DiceGame() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.tapestry),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Row (
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Dicebtn()
            Dicebtn()
        }
    }
}

@Composable
fun Dicebtn() {
    var diceValue by remember { mutableIntStateOf(1) }
    Image(
        painter = painterResource(id = when(diceValue) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }),
        contentDescription = "Dice",
        modifier = Modifier
            .size(200.dp)
            .clickable {
                diceValue = Random.nextInt(1, 7)
            }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Pr02rollthediceadriasanchez_joanlinaresTheme {
        DiceGame()
    }
}