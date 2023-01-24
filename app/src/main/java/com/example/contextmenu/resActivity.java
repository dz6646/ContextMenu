package com.example.contextmenu;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class resActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener, View.OnLongClickListener, View.OnCreateContextMenuListener {
    Intent gi;

    double first, prog;
    boolean choice;

    int index;
    double sum;

    String[] series = new String[20];
    TextView text;

    ListView lv;
    ArrayAdapter<String> adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);

        initArr();

        lv = findViewById(R.id.serie);
        gi = getIntent();
        text = findViewById(R.id.X1);
        first = gi.getDoubleExtra("first", 0);
        prog = gi.getDoubleExtra("prog", 0);
        choice = gi.getBooleanExtra("choice", true);

        adp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, series);

        lv.setOnItemLongClickListener(this);
        lv.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        lv.setOnCreateContextMenuListener(this);


        series[0] = String.format("%.02f", first);
        if (!choice) {
            mathSeries();
        } else {
            engSeries();
        }

        lv.setAdapter(adp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.removeItem(R.id.removeMe);
        menu.add(0, 0, 200, "Main");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String activ = item.getTitle().toString();
        if (activ.equals("Credits")) {
            gi = new Intent(this, Credits.class);
            startActivity(gi);
        } else {
            finish();
        }
        return true;
    }

    public void initArr() {
        int i = 0;

        for (i = 0; i < series.length; i++) {
            series[i] = null;
        }
    }

    public void mathSeries() {
        int i;
        Double temp;
        for (i = 1; i < series.length; i++) {
            temp = Double.parseDouble(series[i - 1]) + prog;
            series[i] = String.format("%.02f", temp);
        }
    }

    public void engSeries() {
        int i;
        Double temp;
        for (i = 1; i < series.length; i++) {
            temp = Double.parseDouble(series[i - 1]) * prog;
            series[i] = String.format("%.02f", temp);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
        index = pos;
        return false;
    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Options");
        menu.add("Position");
        menu.add("Sum from first");
    }

    public void goBack(View view) {
        finish();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String operation = item.getTitle().toString();

        switch (operation) {
            case "Position":
                text.setText("Position: " + (index + 1));
                break;
            case "Sum from first":
                sum = 1;
                //System.out.println(index);
                for (int i = 0; i < index + 1; i++) {
                    sum += Double.parseDouble(series[i]);
                    text.setText("Sum: " + String.format("%.02f", sum));
                    break;
                }

        }
        return true;
    }
}


