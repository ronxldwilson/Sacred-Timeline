package com.example.encrytionapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText messageEditText;
    ImageButton copyButton,clearButton;
    //This method changes the app action bar color
    public void menuColorChange(){
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

        ImageButton copyButton = findViewById(R.id.copy);
        ImageButton clearButton=findViewById(R.id.clear);
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
        //Assigning On-Click Listeners
        decryptButton.setOnClickListener(view -> Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show());
        encryptButton.setOnClickListener(view -> Toast.makeText(getApplicationContext(), "world", Toast.LENGTH_SHORT).show());

        //Copy to Clip Board Functionality
        copyButton.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("EditText", messageEditText.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(MainActivity.this, "Message Copied!", Toast.LENGTH_SHORT).show();

        });

        //clear button functionality
        clearButton.setOnClickListener(view->{
            DialogInterface.OnClickListener dialogClickListener=new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog,int which){
                    switch(which){
                        case DialogInterface.BUTTON_POSITIVE:messageEditText.setText("");
                            Toast.makeText(MainActivity.this,"Text box has been cleared!",Toast.LENGTH_SHORT).show();
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            break;
                    }
                }
            };
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Are you sure?").setPositiveButton("Please clear!",dialogClickListener).setNegativeButton("Don't clear!",dialogClickListener).show();
        });

    }
}
