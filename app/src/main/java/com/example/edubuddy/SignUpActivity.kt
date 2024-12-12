package com.example.edubuddy

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var Auth: FirebaseAuth
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        Auth = FirebaseAuth.getInstance()
        val etFullName = findViewById<EditText>(R.id.FullName)
        val etEmail = findViewById<EditText>(R.id.Email)
        val etPassword = findViewById<EditText>(R.id.Password)
        val cpwd = findViewById<EditText>(R.id.ConfirmPassword)
        val phone = findViewById<EditText>(R.id.PhoneNumber)
        val dateBirth = findViewById<DatePicker>(R.id.dateBirth)
        val btnSubmit = findViewById<Button>(R.id.btnRegister)

        btnSubmit.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val name = etFullName.text.toString()
            val cpassword = cpwd.text.toString()
            if (password != cpassword) {
                Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
            }
            else{
                signUp(name,email,password)
            }

            }

    }
    private fun signUp(name:String,email:String,pwd:String){
        // for all signup that is registration
        Auth.createUserWithEmailAndPassword(email,pwd)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    var intent = Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext,"may already exists.",Toast.LENGTH_SHORT).show()
                }
            }
    }

}
