package ie.setu.pumpitup.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ie.setu.pumpitup.models.Users

class LoginActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ie.setu.pumpitup.R.layout.activity_login)

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()

        // get reference to all views
        var email = findViewById<EditText>(ie.setu.pumpitup.R.id.TextEmailAddress)
        var password = findViewById<EditText>(ie.setu.pumpitup.R.id.TextPassword)
        var login = findViewById<Button>(ie.setu.pumpitup.R.id.login_button)

        val signUpText = findViewById<TextView>(ie.setu.pumpitup.R.id.sign_up_text)
        signUpText.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        // set on-click listener
        login.setOnClickListener {
            val userEmail = email.text.toString()
            val userPassword = password.text.toString()

            if (userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Please enter email and password", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            if (!userEmail.matches(emailPattern.toRegex())) {
                Toast.makeText(this@LoginActivity, "Invalid email format", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Validate password length and complexity
          /*  val passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$"
            if (!userPassword.matches(passwordPattern.toRegex())) {
                Toast.makeText(this@LoginActivity, "Password must be at least 8 characters and include at least one letter and one number", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }*/

            // Sign in with email and password
            firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = firebaseAuth.currentUser
                        val intent = Intent(this@LoginActivity, PumpListActivity::class.java)
                        intent.putExtra("user", Users(userEmail, userPassword))
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

        val emailAddress = Firebase.auth.currentUser

        val passwordResetText = findViewById<TextView>(ie.setu.pumpitup.R.id.forgotten_password_text)
        passwordResetText.setOnClickListener {Firebase.auth.sendPasswordResetEmail(emailAddress.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Email sent.")
                }
            }

        }

    }

    companion object {
        private const val TAG = "LoginActivity"
    }
}
