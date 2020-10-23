package edu.umkc.simplelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast; // Notifications
import android.content.Intent; // Redirect implementation
import android.view.View; // UI widget functionality

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void logoutProcess(View view){
        // Notification of successful logout
        Toast.makeText(HomeActivity.this, "Logout successful!", Toast.LENGTH_SHORT).show();
        // New intent to redirect back to the login screen
        Intent redirect = new Intent(HomeActivity.this, MainActivity.class);
        // Triggers the redirect
        startActivity(redirect);
    }
}
