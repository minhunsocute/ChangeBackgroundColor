package com.example.changebackgroundcolor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private int duration = Toast.LENGTH_SHORT;
    private Button btnExit;
    private EditText txtColorSelected;

    private TextView txtSpyBox;
    private LinearLayout myScreen;
    private String PREFNAME = "myPreFile1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtColorSelected = (EditText)findViewById(R.id.inputText);
        btnExit = (Button) findViewById(R.id.btn1);
        txtSpyBox = (TextView)findViewById(R.id.textView);
        myScreen = (LinearLayout)findViewById(R.id.mainScreen);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish();}
        });
        txtColorSelected.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { /* nothing TODO, needed by interface */ }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* nothing TODO, needed by interface */ }
            @Override
            public void afterTextChanged(Editable s) {
                String chosenColor = s.toString().toLowerCase(Locale.US);
                txtSpyBox.setText(chosenColor);
                setBackgroundColor(chosenColor, myScreen);
            }
        });
        context = getApplicationContext();
        Toast.makeText(context, "onCreate", duration).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(context, "onDestroy", duration).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        String chosenColor = txtSpyBox.getText().toString();
        saveStateData(chosenColor);
        Toast.makeText(context, "onPause", duration).show();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(context, "onRestart", duration).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(context, "onResume", duration).show();
    }
    @Override
    protected void onStart() { super.onStart();
        updateMeUsingSavedStateData();
        Toast.makeText(context, "onStart", duration).show();
    }
    @Override
    protected void onStop() { super.onStop(); Toast.makeText(context, "onStop", duration).show(); }
    private void setBackgroundColor(String chosenColor, LinearLayout myScreen) {
        if (chosenColor.contains("hung")) myScreen.setBackgroundColor(0xffff0000); //Color.RED
        if (chosenColor.contains("hoang")) myScreen.setBackgroundColor(0xff00ff00); //Color.GREEN
        if (chosenColor.contains("dai")) myScreen.setBackgroundColor(0xff0000ff); //Color.BLUE
        if (chosenColor.contains("ton")) myScreen.setBackgroundColor(0xffffffff); //Color.WHITE
        if(chosenColor.contains("name")) myScreen.setBackgroundColor(0xf0ff000);
    }
    private void saveStateData(String chosenColor) {
        SharedPreferences myPrefContainer = getSharedPreferences(PREFNAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor myPrefEditor = myPrefContainer.edit();
        String key = "chosen_background_color", value = txtSpyBox.getText().toString();
        myPrefEditor.putString(key, value);
        myPrefEditor.commit();
    }


    private void updateMeUsingSavedStateData() {
        SharedPreferences myPrefContainer = getSharedPreferences(PREFNAME, Activity.MODE_PRIVATE);
        String key = "chosen_background_color";
        String defaultValue = "ton";
        if (( myPrefContainer != null ) && myPrefContainer.contains(key)){
            String color = myPrefContainer.getString(key, defaultValue);
            setBackgroundColor(color, myScreen);
        }
    }
}