package com.arezzo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.arezzo.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        //Llamado al metodo Login
        binding.btnLogIn.setOnClickListener{
            doLogin()
        }

        //Llamado al metodo Register
        binding.btnRegister.setOnClickListener{
            doRegister()
        }
    }

    private fun doLogin() {
        val email = binding.txtEmail.text.toString()
        val password = binding.txtPassword.text.toString()

        if(email=="" && password==""){
            Toast.makeText(baseContext, "Correo o contraseña vacios", Toast.LENGTH_LONG).show()
        }else{
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful){
                        Log.d("Log In", "Successful")
                        val user = auth.currentUser
                        Toast.makeText(baseContext, "Ingresando a la App", Toast.LENGTH_LONG).show()
                        goNextView(user)
                    }else{
                        Log.d("Log In", "Fail")
                        Toast.makeText(baseContext, "Correo o contraseña incorrectos", Toast.LENGTH_LONG).show()
                        goNextView(null)
                    }
                }
        }
    }

    private fun doRegister() {
        val email = binding.txtEmail.text.toString()
        val password = binding.txtPassword.text.toString()

        if(email=="" && password==""){
            Toast.makeText(baseContext, "Correo o contraseña vacios", Toast.LENGTH_LONG).show()
        }else{
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful){
                        Log.d("Log In", "Successful")
                        val user = auth.currentUser
                        Toast.makeText(baseContext, "Ingresando a la App", Toast.LENGTH_LONG).show()
                        goNextView(user)
                    }else{
                        Log.d("Log In", "Fail")
                        Toast.makeText(baseContext, "Correo ya registrado", Toast.LENGTH_LONG).show()
                        goNextView(null)
                    }
                }
        }
    }

    private fun goNextView(user: FirebaseUser?) {
        if(user != null){
            val intent = Intent(this, PrincipalActivity::class.java)
            startActivity(intent)
        }
    }

    public override fun onStart() {
        super.onStart()
        binding.txtEmail.setText("")
        binding.txtPassword.setText("")
        val existingUser = auth.currentUser
        goNextView(existingUser)
    }
}