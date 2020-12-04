package com.vijaya.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.jar.Attributes;
import java.util.Date;

import android.speech.tts.TextToSpeech;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private ImageButton mSpeakBtn;
    private String input, time, goodbyeString;
    private String name = "user";
    // Quicker to define the phrases here than explicitly for the tts output and the text display
    private static final String askNameString = "What is your name?";
    private static final String helloString = "Hello ";
    private static final String symptomString = "I can understand. Please tell me your symptoms in short.";
    private static final String feverString = "I think you have a fever. Please take this medicine.";



    private TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts=new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                tts.speak("Hello", TextToSpeech.QUEUE_FLUSH, null);
            }
        });


        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    private void nameQuery() {
        // Asks the user their name
        mVoiceInputTv.setText(askNameString);
        tts.speak(askNameString, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void setName(ArrayList<String> result){
        // The code snippet given in the pdf does not work, as it can't find the defines for PREFS or NAME
        // So I did some basic implementation
        input = result.get(0).toString();
        String[] nameArray = input.split(" ");
        name = nameArray[3];
        mVoiceInputTv.setText(helloString + name);
        tts.speak(helloString + name, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void symptomQuery(){
        // If the user says they're not feeling well
        mVoiceInputTv.setText(symptomString);
        tts.speak(symptomString, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void medicineQuery(){
        // If the users asks what medicine they should take
        mVoiceInputTv.setText(feverString);
        tts.speak(feverString, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void getTime(){
        // If the user asks the time.
        // Only outputs to military time right now
        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm");
        Date now = new Date();
        String[] strDate = sdfDate.format(now).split(":");
        if (strDate[1].contains("00")){
            strDate[1] = "o'clock";
        }
        time = "The time is " + sdfDate.format(now);
        mVoiceInputTv.setText(time);
        tts.speak(time, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void goodBye(){
        goodbyeString = "Thank you too " + name + ". Take care.";
        mVoiceInputTv.setText(goodbyeString);
        tts.speak(goodbyeString, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mVoiceInputTv.setText(result.get(0));
                    if (result.get(0).equals("hello")){
                        nameQuery();
                    }
                    if (result.get(0).equals("I am not feeling well what should I do") || result.get(0).equals("I'm not feeling well what should I do")){
                        symptomQuery();
                    }
                    if (result.get(0).equals("what medicines should I take") || result.get(0).equals("what medicine should I take")){
                        medicineQuery();
                    }
                    if (result.get(0).contains("my name is")){
                        setName(result);
                    }
                    if (result.get(0).equals("what time is it")){
                        getTime();
                    }
                    if (result.get(0).equals("thank you my medical assistant")){
                        goodBye();
                    }
                }
                break;
            }

        }

    }
}