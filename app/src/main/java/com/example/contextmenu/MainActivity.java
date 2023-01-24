package com.example.contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    String series[];
    ToggleButton choose;
    EditText first;
    EditText progress;
    Intent si;
    Button pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        choose = findViewById(R.id.toggleButton);
        first = findViewById(R.id.first);
        progress = findViewById(R.id.progress);
        series = new String[20];
        si = new Intent(this, resActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean inputCheck(String st)
    {
        int i = 0;

        for(i = 0; i < st.length(); i++)
        {
            if((st.charAt(i) < '0' || st.charAt(i) > '9') && st.charAt(i) != '.')
            {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String color = item.getTitle().toString();
        if(color.equals("Results"))
        {
            String temp = first.getText().toString();
            if(!temp.isEmpty()) {

                si.putExtra("choice", choose.isChecked());
                if (inputCheck(temp)) {
                    si.putExtra("first", Double.parseDouble(temp));
                } else {
                    si.putExtra("first", 0);
                }
                temp = progress.getText().toString();
            }
            temp = progress.getText().toString();
            if(!temp.isEmpty()) {
                if (inputCheck(temp)) {
                    si.putExtra("prog", Double.parseDouble(temp));
                } else {
                    si.putExtra("prog", 0);
                }
                startActivity(si);
            }
        }
        else if(color.equals("Credits"))
        {
            si = new Intent(this, Credits.class);
            startActivity(si);
        }

        return true;
    }
}