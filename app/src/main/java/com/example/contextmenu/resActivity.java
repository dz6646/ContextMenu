package com.example.contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class resActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Intent gi;

    double first, prog;
    boolean choice;

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

        first = gi.getDoubleExtra("first", 0);
        prog = gi.getDoubleExtra("prog", 0);
        choice = gi.getBooleanExtra("choice", true);

        adp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, series);
        lv.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        lv.setOnItemClickListener(this);

        series[0] = String.format("%.02f", first);
        if(choice)
        {
            mathSeries();
        }
        else
        {
            engSeries();
        }

        lv.setAdapter(adp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.removeItem(R.id.removeMe);
        menu.add(0, 0, 200,"Main");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String activ = item.getTitle().toString();
        if(activ.equals("Credits"))
        {
            gi = new Intent(this, Credits.class);
            startActivity(gi);
        }
        else
        {
            finish();
        }
        return true;
    }

    public void initArr()
    {
        int i = 0;

        for(i = 0; i < series.length; i++)
        {
            series[i] = null;
        }
    }

    public void mathSeries()
    {
        int i;
        Double temp;
        for(i = 1; i < series.length; i++)
        {
            temp = Double.parseDouble(series[i - 1]) + prog;
            series[i] = String.format("%.02f", temp);
        }
    }

    public void engSeries()
    {
        int i;
        Double temp;
        for(i = 1; i < series.length; i++)
        {
            temp = Double.parseDouble(series[i - 1]) * prog;
            series[i] = String.format("%.02f", temp);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
        if(pos == 0)
        {

        }
        else
        {
            int i;
            double sum = Double.parseDouble(series[0]);

            for (i = 1; i < pos; i++) {
                sum += Double.parseDouble(series[i]);
            }
            String st = String.format("%.02f", sum);

        }
        String st = String.format("%.02f", prog);

    }



    public void goBack(View view) {
        finish();
    }
}