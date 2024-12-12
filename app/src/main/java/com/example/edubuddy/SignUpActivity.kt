package com.example.edubuddy

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {


    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        // auth = FirebaseAuth.getInstance()

        val etName = findViewById<EditText>(R.id.FullName)
        val etEmail = findViewById<EditText>(R.id.Email)
        val etPassword = findViewById<EditText>(R.id.Password)
        val btnSubmit = findViewById<Button>(R.id.btnRegister)

        btnSubmit.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                /***   auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                } else {
                Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
                }
                } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
                 ***/
            }
        }
    }
}
