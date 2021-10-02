package com.example.encrytionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//MAIN ACTIVITY
public class MainActivity extends AppCompatActivity {

    //ON CREATE
    //Creates the Activity UI
    //add a dropdown box in plact of the textfield (http://www.ahotbrew.com/android-dropdown-spinner-example/)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}