package com.example.ludo.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button level1;
    private Button level2;
    Bundle b = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        level1 = (Button)findViewById(R.id.buttonlevel1);
        level1.setOnClickListener(this);
        level2 = (Button)findViewById(R.id.buttonlevel2);
        level2.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        if (v == level1)
        {

            //Log.d("My message", String.valueOf(textchampsaisie.getText()));
            b.putString("level", String.valueOf(1));
            Intent intention = new Intent(this, ListeGrille.class);
            intention.putExtras(b);
            startActivity(intention);
        }

        if (v == level2)
        {

            //Log.d("My message", String.valueOf(textchampsaisie.getText()));
            b.putString("level", String.valueOf(2));
            Intent intention = new Intent(this, ListeGrille.class);
            intention.putExtras(b);
            startActivity(intention);
        }
    }
}
