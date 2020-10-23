package edu.umkc.simplelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast; // Notifications
import android.content.Intent; // Redirect implementation
import android.view.View; // UI widget functionality

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginProcess(View view) { // Main landing page when login isn't done

        // Gets the username input
        EditText usernameCtrl = (EditText) findViewById(R.id.userNameText);
        // Gets the password input
        EditText passwordCtrl = (EditText) findViewById(R.id.passwordText);
        // Sets the username local variable
        String username = usernameCtrl.getText().toString();
        // Sets the password local variable
        String password = passwordCtrl.getText().toString();

        boolean validationFlag = false;

        // If both the username and password fields aren't empty
        if(!username.isEmpty()&&!password.isEmpty()) {
            if (username.equals("Admin") && password.equals("Admin")) {
                validationFlag = true;
            }
        }
        if(!validationFlag){
            // Runs a notification if the entered username and password isn't valid
            Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
        else{
            // Runs notification if entered username and password are valid
            Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
            // Sets up the redirect to the home page
            Intent redirect = new Intent(MainActivity.this, HomeActivity.class);
            // Starts up the redirect
            startActivity(redirect);
        }
    }
}