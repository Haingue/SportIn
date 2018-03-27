package fr.univ_lille1.iutinfo.sportin;

/**
 * Created by busschaa on 23/03/18.
 */
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import fr.univ_lille1.iutinfo.communication.ConnexionServ;

public class PageNav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<String> listProg= new ArrayList<>();
    private List<String> listRenc= new ArrayList<>();
    private String pass="titi:titi";
    JSONArray array;

    private String url = "http://172.18.49.2:8080/v1/events";

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

        // Initialize a new RequestQueue instance
        RequestQueue queue = Volley.newRequestQueue(this);



        JsonArrayRequest JsonRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        int nb;

                            nb=response.length();


                        for (int i=0;i<nb;i++) {

                            try {

                                JSONObject object =response.getJSONObject(i);

                                String nomEvent =object.getString("label");
                                String jour=object.getString("dateEvent");
                                int prix = object.getInt("price");
                                int nbParticipantNeeded = object.getInt("participants");
                                Toast.makeText(getApplicationContext(),object.toString(),Toast.LENGTH_LONG).show();
                                String res=nomEvent+"\n"+jour+"\n"+prix+"€"+"\n0"+"/"+nbParticipantNeeded;
                                Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
                                listProg.add(res);



                            }catch(JSONException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        error.printStackTrace();
                        //TODO: handle failure

                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();

                    }
                }) {

            public Map<String, String> getHeaders(){
                Map<String, String>  headers = new HashMap<String, String>();
                String credentials = pass;
                String auth = "Basic "+ Base64.encodeToString(credentials.getBytes(),Base64.NO_WRAP);
                headers.put("Accept", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }

        };
        ;

        // Add JsonObjectRequest to the RequestQueue
        queue.add(JsonRequest);




        listRenc = new ArrayList<>();
        //Initialisation de la liste de manière automatisée
        /*for(int i = 0;i<10 ; i++){ //TODO ajouter 10 rencontres interessantes pour l'utilisateur
            String nomSport ="Nom du sport";
            String nomSalle="Nom de la salle";
            String jour = "Jour";
            String heure = "heure";
            String prix="Prix";
            String nbInscrits = "nbInscrits";
            String nbParticipantNeeded = "nbParticipantNeeded";
            listRenc.add(nomSport+"\n"+nomSalle+"\n"+jour+","+heure+"\n"+prix+"€"+"\n"+nbInscrits+"/"+nbParticipantNeeded);
        }*/

        //Initialisation de la liste de manière statique
        listRenc.add("Running"+"\n"+"Vestiaires du bâtiment A"+"\n"+
                "Jeudi"+","+"12h30"+"\n"+"0"+"€"+"\n" +
                "8"+"/"+"illimité");

        listRenc.add("Futsal"+"\n"+"Urban Socer, Lezennes"+"\n"+
                "Demain"+","+"18h00"+"\n"+"7"+"€"+"\n" +
                "8"+"/"+"10");

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
            Intent i=new Intent(this,PageProfil.class);
            startActivity(i);
        } else if (id == R.id.nav_chat) {
            //TODO envoyer à la page de chat
            Intent i=new Intent(this,PageCreerEvent/*PageChat*/.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Affiche la liste des "Mes rencontres"
     * et change la couleur des boutons rencontre et programme
     * @param view
     */
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

    /**
     * Affiche la liste des "Rencontres sportives"
     * et change la couleur des boutons rencontre et programme
     * @param view
     */
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

    /**
     * Affiche la page de formulaire pour créer un évènement
     * @param view
     */
    public void afficherForm(View view){
        Intent i=new Intent(this,PageCreerEvent.class);
        startActivity(i);
    }
}

