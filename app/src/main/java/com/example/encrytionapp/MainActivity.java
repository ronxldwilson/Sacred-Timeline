package com.example.encrytionapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
//To Do
//The Features to be added in the app
//1. Figure out a way of storing the Names of new user and their public key in a secure manner,
//   something like a excel table like functionality
//2. Add a method by which the name stored gets visible in the drop down menu(Start working on it, once task 2 is finished)
//3. Create a Navigation drawer menu on the top left side(Hamburger icon):This will contain various other fragments like Home,
//   Tutorial, Share Public Key, about us, Delete all data(Create just the menu for now, we can add the details later on).
//4.
public class MainActivity extends AppCompatActivity {

    //Enumerator for possible States of the activity
    public enum State {
        PROCESSING,     //Short duration when buttons are being loaded, event-listeners are being attached, etc.
        READY,          //Ready-state, waiting for user to hit Encrypt/Decrypt button
        ENCRYPTED,      //User has hit encrypt button once  <--  Copy Button can be made visible only during this state
        DECRYPTED       //User has hit decrypt button once
    }
    State currentState;

    //Makes sense to use these as booleans instead of States (see ^), since they can be performed simultaneously/not in order
    boolean messageEntered;
    boolean recipientSelected;

    EditText messageEditText;
    ImageButton copyButton,clearButton;


    //This method changes the app action bar color
    public void menuColorChange() {
        setContentView(R.layout.activity_main);
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable;
        colorDrawable = new ColorDrawable(Color.parseColor("#0F9D58"));

        // Set BackgroundDrawable
        assert actionBar != null;
        actionBar.setBackgroundDrawable(colorDrawable);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menuColorChange();

        //Defines current state of the activity, initial state is PROCESSING by default
        currentState = State.PROCESSING;

        ImageButton copyButton = findViewById(R.id.copy);
        ImageButton clearButton = findViewById(R.id.clear);
        Button addUserButton = findViewById(R.id.addUser);
        Button decryptButton = findViewById(R.id.decryptButton);
        Button encryptButton = findViewById(R.id.encryptButton);
        EditText messageEditText = findViewById(R.id.message);

        //Opens Activity_main_2 where the user can add the public keys of contacts and their names
        addUserButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });

        //considering assignment of on-click listeners
        //Assigning Encrypt & Decrypt Button On-Click Listeners
        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decryptMessage();
            }
        });
        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encryptMessage();
            }
        });

        //Copy to Clip Board Functionality
        copyButton.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("EditText", messageEditText.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(MainActivity.this, "Message Copied!", Toast.LENGTH_SHORT).show();

        });

        //clear button functionality
        clearButton.setOnClickListener(view -> {
            //State goes back to READY, waiting for new Encryption/Decryption request, only if CLEAR button is pressed after encryption
            if(currentState == State.ENCRYPTED)
                currentState = State.READY;

            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which){
                    switch(which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            messageEditText.setText("");
                            Toast.makeText(MainActivity.this,"Text box has been cleared!",Toast.LENGTH_SHORT).show();
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            break;
                    }
                }
            };
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure?").setPositiveButton("Please clear!",dialogClickListener).setNegativeButton("Don't clear!",dialogClickListener).show();
        });

        //current state is updated to READY after completion of all initial processes
        currentState = State.READY;
    }

    private void encryptMessage() {
        //Encryption Algorithm
        currentState = State.ENCRYPTED;
        Toast.makeText(getApplicationContext(), "Message Encrypted", Toast.LENGTH_SHORT).show();
    }

    private void decryptMessage() {
        //Decryption Algorithm
        currentState = State.DECRYPTED;
        Toast.makeText(getApplicationContext(), "Message Decrypted", Toast.LENGTH_SHORT).show();
    }
}
