package com.example.ludo.sudoku;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;
/**
 * Created by Ludo on 03/02/2017.
 */

public class Dessin extends View implements View.OnTouchListener {
    int border = 3; // rectBorder
    int borderCadre = 8;
    int largeur;
    int hauteur;
    int posXTouch;
    int posYTouch;
    int nombreSelectionner;
    Paint paintTexte = new Paint();
    Paint paintTexteDepart = new Paint();
    Paint paint = new Paint();
    Paint paintBackgroundBas = new Paint();
    Paint paintCadre = new Paint();
    Paint paintError = new Paint();
    int textSize = 80;
    int BarBas;
    int cote;
    int[][] vGrille = new int[9][9];
    String res ="008203500009670408346050702430010059967005001000496203280034067703500904004107020";
    GrilleGene grille = new GrilleGene();
    Check check = new Check();
    int [][] grilleDepart =  grille.getGrille(res);
    public Dessin(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnTouchListener(this);




    }

    @Override
    public void onDraw(Canvas canvas) {
        largeur = canvas.getWidth();
        hauteur = canvas.getHeight();
        paintTexte.setColor(Color.BLACK);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(border);
        paintCadre.setStyle(Paint.Style.STROKE);
        paintCadre.setStrokeWidth(borderCadre);
        int width = (largeur / 9);
        int height = (hauteur / 9);
        int largeurCadre = largeur/3;
        paintTexte.setTextSize(textSize);
        paintTexteDepart.setTextSize(textSize);
        paintTexteDepart.setColor(Color.BLUE);
        paintError.setTextSize(textSize);
        paintError.setColor(Color.RED);
        cote = width < height ? width : height;

        int bwd = (largeur - (9 * cote)) / 2;

        for (int x = 1; x < 4; x++) {
            for (int y = 1; y < 4; y++) {

                canvas.drawRect(bwd + (x - 1 * largeurCadre), border + y - 1 * largeurCadre, bwd + (x * largeurCadre), border + y * largeurCadre, paintCadre);

            }
        }

        for (int x = 1; x < 10; x++) {
            for (int y = 1; y < 10; y++) {

                canvas.drawRect(bwd + (x - 1 * cote), border + y - 1 * cote, bwd + (x * cote), border + y * cote, paint);
                if(grilleDepart[x-1][y-1] > 0){
                    canvas.drawText(String.valueOf(grilleDepart[x-1][y-1]),(x-1)  * cote/2 + (x*cote/2)-textSize/4, y*cote - textSize/3 + (cote / 2 - textSize/3), paintTexteDepart);
                   // Log.d("Deubg", String.valueOf(grilleDepart[x-1][y-1]));
                }
                if (vGrille[x - 1][y - 1] > 0) {


                    if(check.checkGlobal(grilleDepart,vGrille,vGrille[x-1][y-1],x-1,y-1)) {
                        canvas.drawText(String.valueOf(vGrille[x - 1][y - 1]), (x - 1) * cote / 2 + (x * cote / 2) - textSize / 4, y * cote - textSize / 3 + (cote / 2 - textSize / 3), paintTexte);
                    }
                    else{
                        canvas.drawText(String.valueOf(vGrille[x - 1][y - 1]), (x - 1) * cote / 2 + (x * cote / 2) - textSize / 4, y * cote - textSize / 3 + (cote / 2 - textSize / 3), paintError);
                    }



                }
            }

            }
        for (int x = 1; x <= 9; x++) {
            paintBackgroundBas.setColor(Color.BLUE);
            BarBas = hauteur - 100;
            paintBackgroundBas.setStrokeWidth(2);
            paintBackgroundBas.setStyle(Paint.Style.STROKE);


            canvas.drawRect(bwd + (x - 1 * cote), BarBas - cote, bwd + (x * cote), 2 + BarBas, paintBackgroundBas);
            canvas.drawText(String.valueOf(x), (x - 1) * cote / 2 + (x * cote / 2) - textSize / 4, BarBas - (cote / 2 - textSize / 3), paintTexte);

        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if( BarBas > y && y > BarBas - cote  ) {
                    nombreSelectionner = x / cote + 1;
                    Log.d("Nombre", String.valueOf(nombreSelectionner));
                }
                else if(y < cote*9 ){
                    posXTouch = x/cote + 1;
                    posYTouch = y/cote +1;
                    Log.d("Pos", String.valueOf(posXTouch + " " + posYTouch));
                }
                if(nombreSelectionner > 0 && posYTouch > 0 && check.checkValeurDepart(grilleDepart,posXTouch-1,posYTouch-1))
                {
                    vGrille[posXTouch-1][posYTouch-1] = nombreSelectionner;
                    nombreSelectionner = 0;
                    posYTouch = 0;
                    posXTouch = 0;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                break;
        }
        this.invalidate();
        return true;
    }



}

class GrilleGene{


    public int[][] getGrille(String string){
        int[][] grille = new int[9][9];
        int compteur = 0;


        for(int i = 0; i < 9; i++) {
            for(int o = 0; o < 9; o++){

                char test = string.charAt(compteur);


                grille[i][o] = Integer.valueOf(Integer.parseInt(String.valueOf(test)));
                //Log.d("Ici", String.valueOf(Integer.parseInt(String.valueOf(test))));
                compteur += 1;

            }

        }
        return grille;

    }

}


class Check{

    public boolean checkValeurDepart(int[][]grilleDepart, int posX, int posY){


        return grilleDepart[posX][posY] == 0;
    }
    public boolean checkGlobal(int[][] grilleDepart,int[][]vGrille,int nombre, int posX, int posY){
        return checkLigne(grilleDepart,vGrille,posX,posY,nombre) && checkCarre(grilleDepart,vGrille,posX,posY,nombre);
    }



    public boolean checkLigne(int[][] grilleDepart,int[][]vGrille, int posX, int posY, int nombreSelectionne){

        boolean possible = true;
        int compteur = 0;

        while(possible && compteur <9){
            if(grilleDepart[compteur][posY] == nombreSelectionne
                    ||grilleDepart[posX][compteur] == nombreSelectionne
                    ||vGrille[compteur][posY] == nombreSelectionne && compteur != posX
                    ||vGrille[posX][compteur] == nombreSelectionne && compteur != posY
                    )
            {
                possible = false;
            }
            compteur += 1;
            //Log.d("check", String.valueOf(possible));
        }

        return possible;
    }

    public boolean checkCarre(int[][] grilleDepart,int[][]vGrille, int posX, int posY, int nombreSelectionne){

        boolean possible = true;
        //Check horizontal
        int compteurHori = (posX/3)*3;
        int maxHori = compteurHori+3;
        int compteurVerti = (posY/3)*3;
        int maxVerti = compteurVerti + 3;
        Log.d("compteurVerti", String.valueOf(compteurVerti));
        Log.d("compteurHori", String.valueOf(compteurHori));
        while(possible && compteurHori < maxHori){
            while(possible && compteurVerti < maxVerti) {
                if (grilleDepart[compteurHori][compteurVerti] == nombreSelectionne
                        || vGrille[compteurHori][compteurVerti] == nombreSelectionne
                        && compteurHori != posX
                        && compteurVerti != posY
                        ) {
                    possible = false;
                }
                compteurVerti += 1;
            }
            compteurVerti = 0;
            compteurHori += 1;
            //Log.d("check", String.valueOf(possible));
        }



        return possible;
    }


}
