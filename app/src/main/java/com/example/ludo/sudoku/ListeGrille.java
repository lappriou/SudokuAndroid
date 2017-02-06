package com.example.ludo.sudoku;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ListeGrille extends AppCompatActivity {
    SimpleCursorAdapter mAdapter;
    Bundle b;
    Context context = this;
    private ListView listGrille;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_grille);

        GrilleObjet[] values = new GrilleObjet[100];
        Random rand = new Random();
        for(int i = 0; i < 100; i++){

            values[i] = new GrilleObjet(i,1, rand.nextInt(100));
        }

        listGrille = (ListView) findViewById(R.id.listgrille);

        ArrayAdapter<GrilleObjet> adapter = new ArrayAdapter<GrilleObjet>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        // on assigne l'adapter à notre list
        listGrille.setAdapter(adapter);

        listGrille.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> a, View v, int position, long id)
            {
                //b.putString("level", String.valueOf(2));
                Intent intention = new Intent(context, Grille.class);
                //intention.putExtras(b);
                startActivity(intention);
            }

        });






    }
    }


class GrilleObjet {
    int id;
    int level;
    int percent;


    public GrilleObjet(int id, int level, int Percent){

        this.id = id;
        this.level = level;
        this.percent = Percent;
    }

    public String toString()
    {
        return id + "  Niveau: " + level + " " + percent + "%";
    }
}

