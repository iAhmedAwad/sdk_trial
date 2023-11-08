package com.example.sdk_trial

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.sdk_trial.ui.theme.Sdk_trialTheme
import com.example.trianglzsdk.LoyaltyActivity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val trigger = LoyaltyActivity.trigger
        setContent {
            Sdk_trialTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
                ) {
                    Greeting("Android", modifier = Modifier.clickable {
                        startActivity(Intent(this@MainActivity, LoyaltyActivity::class.java))
                    })
                }
            }
        }
            lifecycleScope.launch {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
         //  repeatOnLifecycle(Lifecycle.State.CREATED) {
            trigger.collect{
                println("collecting... $it")
                if (it==1){
                    startActivity(Intent(this@MainActivity, AppLockActivity::class.java))

                }
            }

          //  }
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier, fontSize = 32.sp

    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Sdk_trialTheme {
        Greeting("Android")
    }
}