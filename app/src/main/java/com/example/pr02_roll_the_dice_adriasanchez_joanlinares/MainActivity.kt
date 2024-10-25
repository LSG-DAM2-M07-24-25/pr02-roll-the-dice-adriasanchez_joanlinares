package com.example.pr02_roll_the_dice_adriasanchez_joanlinares

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
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
    var dice1 by remember { mutableIntStateOf(1) }
    var dice2 by remember { mutableIntStateOf(1) }
    var credits by remember { mutableStateOf(10) }
    var context: Context = LocalContext.current

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

        Column {
            Image(
                painter = painterResource(id = R.drawable.lasallegambling),
                contentDescription = "Dice logo",
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "Credits: $credits",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Button( onClick = {
                if (credits >= 2) {
                    credits -= 2
                    dice1 = Random.nextInt(1, 7)
                    dice2 = Random.nextInt(1, 7)
                    if (dice1 == dice2 && dice1 == 6) {
                        Toast.makeText(context, "MEGA JACKPOT!!!", Toast.LENGTH_SHORT).show()
                        credits += 10
                    } else if (dice1 == dice2) {
                        Toast.makeText(context, "JACKPOT!", Toast.LENGTH_SHORT).show()
                        credits += 5
                    }
                } else {
                    Toast.makeText(context, "Not enough credits", Toast.LENGTH_SHORT).show()
                }
            },
                modifier = Modifier.align(Alignment.CenterHorizontally))
            {
                Text("Roll the dice")
            }

            Button( onClick = {
                credits = 10
            },
                modifier = Modifier.align(Alignment.CenterHorizontally))
            {
                Text("Reset credits")
            }

            Row (
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Dicebtn(dice = dice1, changeImage = {
                    if (credits >= 1) {
                        credits -= 1
                        dice1 = Random.nextInt(1, 7)
                        if (dice1 == dice2 && dice1 == 6) {
                            Toast.makeText(context, "MEGA JACKPOT!!!", Toast.LENGTH_SHORT).show()
                            credits += 10
                        } else if (dice1 == dice2) {
                            Toast.makeText(context, "JACKPOT!", Toast.LENGTH_SHORT).show()
                            credits += 5
                        }
                    } else {
                        Toast.makeText(context, "Not enough credits", Toast.LENGTH_SHORT).show()
                    }
                })

                Dicebtn(dice = dice2, changeImage = {
                    if (credits >= 1) {
                        credits -= 1
                        dice2 = Random.nextInt(1, 7)
                        if (dice1 == dice2 && dice1 == 6) {
                            Toast.makeText(context, "MEGA JACKPOT!!!", Toast.LENGTH_SHORT).show()
                            credits += 10
                        } else if (dice1 == dice2) {
                            Toast.makeText(context, "JACKPOT!", Toast.LENGTH_SHORT).show()
                            credits += 5
                        }
                    } else {
                        Toast.makeText(context, "Not enough credits", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
}

@Composable
fun Dicebtn(dice: Int, changeImage: () -> Unit, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = when(dice) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }),
        contentDescription = "Dice",
        modifier = modifier
            .size(180.dp)
            .clickable {
                changeImage()
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
