package ie.setu.pumpitup.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ie.setu.pumpitup.R
import ie.setu.pumpitup.models.Users

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // get reference to all views
        var email= findViewById(R.id.TextEmailAddress) as EditText
        var password = findViewById(R.id.TextPassword) as EditText
        var login = findViewById(R.id.login_button) as Button


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
            val passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"
            if (!userPassword.matches(passwordPattern.toRegex())) {
                Toast.makeText(this@LoginActivity, "Password must be at least 8 characters and include at least one letter and one number", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }


            // Create a new User object with the email and password
            val user = Users(userEmail, userPassword)

            // Create a new Intent to navigate to PumpListActivity
            val intent = Intent(this@LoginActivity, PumpListActivity::class.java)

            // Add the User object as an extra to the Intent
            intent.putExtra("user", user)

            // Start the PumpListActivity using the Intent
            startActivity(intent)
        }

    }
}

//References
//https://stackoverflow.com/questions/33551972/is-there-a-convenient-way-to-create-parcelable-data-classes-in-android-with-kotl