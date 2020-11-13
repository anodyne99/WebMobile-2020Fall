package com.example.vijaya.androidhardware;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StorageActivity extends AppCompatActivity {
    EditText txt_content;
    EditText contentToDisplay;
    String FILENAME = "MyAppStorage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        txt_content = (EditText) findViewById(R.id.id_txt_mycontent);
        contentToDisplay = (EditText) findViewById(R.id.id_txt_display);
    }

    public void saveTofile(View v) throws IOException {

        // ICP Task4: Write the code to save the text

        /* Initial setup and assignment of local variables*/
        String input = txt_content.getText().toString();
        FileOutputStream output = null;

        /* Starts the output stream */
        output = openFileOutput(FILENAME, MODE_PRIVATE);
        output.write(input.getBytes());

        /* Closes and clears the output stream after it's done writing */
        txt_content.getText().clear();
        if (output != null) {
            output.close();
        }

    }

    public void retrieveFromFile(View v) throws IOException {

        // ICP Task4: Write the code to display the above saved text

        /* Initial assignments and initializations of local variables */
        FileInputStream input = null;
        input= openFileInput(FILENAME);
        InputStreamReader inputStream = new InputStreamReader(input);
        BufferedReader buff = new BufferedReader(inputStream);
        StringBuilder build = new StringBuilder();
        String textOut;

        /* While loop for reading and outputting the buffer until it's empty */
        while ((textOut = buff.readLine()) != null) {
            build.append(textOut).append(" ");
        }
        txt_content.setText(build.toString());

        /* Closing the file stream */
        input.close();

        /* Displaying the saved text */
        contentToDisplay.setText(build);
        contentToDisplay.setVisibility(View.VISIBLE);
    }
}
