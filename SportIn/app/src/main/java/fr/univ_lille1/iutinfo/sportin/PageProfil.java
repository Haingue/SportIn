package fr.univ_lille1.iutinfo.sportin;

/**
 * Created by parissej and martele on 26/03/18.
 */
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import fr.univ_lille1.iutinfo.communication.ConnexionServ;
public class PageProfil extends AppCompatActivity {

    private List<String> listSports;
    ListView list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialisation de la liste contenant les sports évalués par l'utilisateur
        listSports = new ArrayList<>();
        for(int i = 0;i<5 ; i++){ //TODO le nombre de sports que l'utilisateur à selectionné (à remplacer)
            String nomSport ="Nom du sport"; //TODO remplacer avec le nom du sport
            int rating = i; //TODO remplacer avec la bonne valeur
            String ratingString="";
            for(int nbEtoiles = 0;nbEtoiles<rating ; nbEtoiles++){
                ratingString+="*";
            }
            listSports.add(nomSport+"     "+ratingString);
        }

        //Initialisation de la listeView
        list = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(PageProfil.this,
                android.R.layout.simple_list_item_1, listSports);
        list.setAdapter(adapter);
    }

    /**
     * Méthode liée au bouton "Modifier mon compte"
     * qui doit envoyer vers la page de modification de compte
     * @param view
     */
    public void modifierCompte(View view) {
        //TODO envoyer sur la future page de modification de compte
        Intent i=new Intent(this,PageProfil.class);
        startActivity(i);
    }

}