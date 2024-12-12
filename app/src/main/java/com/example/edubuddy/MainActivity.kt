package com.example.edubuddy

import ResourceFinderActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.edubuddy.ui.theme.EduBuddyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            setContentView(R.layout.homepage)
            val btnRegister = findViewById<Button>(R.id.btnRegister)
            val btnLogin = findViewById<Button>(R.id.btnLogin)
            val btnFindResources = findViewById<Button>(R.id.btnFindResources)

            btnRegister.setOnClickListener {
                startActivity(Intent(this, SignUpActivity::class.java))
            }

            btnLogin.setOnClickListener {
                startActivity(Intent(this, LoginActivity::class.java))
            }

            btnFindResources.setOnClickListener {

                startActivity(Intent(this, ResourceFinderActivity::class.java))
            }
        }
    }
}
