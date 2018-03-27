package fr.univ_lille1.iutinfo.sportin;

/**
 * Created by busschaa on 23/03/18.
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

public class PageNav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<String> listProg;
    private List<String> listRenc;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*Intent connect = new Intent(this, ConnexionServ.class);
        startActivity(connect);*/

        //TODO lister les events de la bdd








        listProg = new ArrayList<>();
        //TODO modifier le for (en while enventuellement?)
        for(int i = 0;i<4 ; i++){ //TODO ajouter toutes les rencontres futures de l'utilisateur
            String nomSport ="Futsal";
            String nomSalle="Footsal, Villeneuve d'Ascq Cousinerie";
            String jour = "Mercredi";
            String heure = "19h00";
            String prix="8";
            String nbInscrits = "8";
            String nbParticipantNeeded = "10";
            listProg.add(nomSport+"\n"+nomSalle+"\n"+jour+","+heure+"\n"+prix+"€"+"\n"+nbInscrits+"/"+nbParticipantNeeded);
        }

        listRenc = new ArrayList<>();
        for(int i = 0;i<10 ; i++){ //TODO ajouter 10 rencontres interessantes pour l'utilisateur
            String nomSport ="Nom du sport";
            String nomSalle="Nom de la salle";
            String jour = "Jour";
            String heure = "heure";
            String prix="Prix";
            String nbInscrits = "nbInscrits";
            String nbParticipantNeeded = "nbParticipantNeeded";
            listRenc.add(nomSport+"\n"+nomSalle+"\n"+jour+","+heure+"\n"+prix+"€"+"\n"+nbInscrits+"/"+nbParticipantNeeded);
        }

        ((Button) findViewById(R.id.renc)).performClick();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

       /* switch (id) {
            case R.id.nav_profil:
                nav_profil(item);
                return true;
            case R.id.nav_event:
                nav_event(item);
                return true;
            case R.id.nav_chat:
                return false;
            default:
                return false;
        }*/


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_event) {
            Intent i=new Intent(this,PageNav.class);
            startActivity(i);
        } else if (id == R.id.nav_profil) {
            Intent i=new Intent(this,/*PageCreerEvent*/PageProfil.class);
            startActivity(i);
        } else if (id == R.id.nav_chat) {
            Intent i=new Intent(this,PageCreerEvent.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void programme(View view){
      Button p = (Button) findViewById(R.id.prog);
        p.setTextColor(Color.WHITE);
        p.setBackgroundColor(Color.BLACK);

        Button r = (Button) findViewById(R.id.renc);
        r.setTextColor(Color.BLACK);
        r.setBackgroundColor(Color.LTGRAY);

        list = (ListView) findViewById(R.id.list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(PageNav.this,
                android.R.layout.simple_list_item_1, listProg);
        list.setAdapter(adapter);

    }

    public void rencontre(View view){
        Button r = (Button) findViewById(R.id.renc);
        r.setTextColor(Color.WHITE);
        r.setBackgroundColor(Color.BLACK);

        Button p = (Button) findViewById(R.id.prog);
        p.setTextColor(Color.BLACK);
        p.setBackgroundColor(Color.LTGRAY);

         list= (ListView) findViewById(R.id.list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(PageNav.this,
                android.R.layout.simple_list_item_1, listRenc);
        list.setAdapter(adapter);
    }

    public void afficherForm(View view){
        Intent i=new Intent(this,PageCreerEvent.class);
        startActivity(i);
    }
}

