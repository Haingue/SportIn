package fr.univ_lille1.iutinfo.sportin;

/**
 * Created by busschaa on 23/03/18.
 */
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

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
import java.util.List;

import fr.univ_lille1.iutinfo.communication.ConnexionServ;

public class PageNav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String[] prenoms;

    private String[] noms;

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

        noms = new String[]{
                "Tennis\nFOS Tennis\nSamedi, 11h00\n15€\n3/4",
                "Tennis\nFOS Tennis\nSamedi, 11h00\n15€\n2/4",
                "Tennis\nFOS Tennis\nSamedi, 11h00\n15€\n3/4",
                "Tennis\nFOS Tennis\nSamedi, 11h00\n15€\n1/4",
                "Futsal\nFootsal blabla\nMercredi,19h00\n8€\n8/10",
                "Futsal\nFootsal blabla\nMercredi,19h00\n8€\n8/10",
                "Futsal\nFootsal blabla\nMercredi,19h00\n8€\n8/10",
                "Futsal\nFootsal blabla\nMercredi,19h00\n8€\n8/10",
                "Futsal\nFootsal blabla\nMercredi,19h00\n8€\n8/10",
                "Futsal\nFootsal blabla\nMercredi,19h00\n8€\n8/10"
        };

        prenoms = new String[]{
                "Futsal\nFootsal blabla\nMercredi,19h00\n8€\n8/10",
                "Futsal\nFootsal blabla\nMercredi,19h00\n8€\n8/10",
                "Futsal\nFootsal blabla\nMercredi,19h00\n8€\n8/10",
                "Futsal\nFootsal blabla\nMercredi,19h00\n8€\n8/10",
                "Futsal\nFootsal blabla\nMercredi,19h00\n8€\n8/10",
                "Futsal\nFootsal blabla\nMercredi,19h00\n8€\n8/10",
                "Futsal\nFootsal blabla\nMercredi,19h00\n8€\n8/10",
                "Futsal\nFootsal blabla\nMercredi,19h00\n8€\n8/10",
                "Futsal\nFootsal blabla\nMercredi,19h00\n8€\n8/10",
                "Futsal\nFootsal blabla\nMercredi,19h00\n8€\n8/10"
        };

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            System.out.println("===============================\nClick On Profil\n==================================\n");
            //Toast.makeText(getApplicationContext(),ConnexionServ.getInstance().getUser().toString(), Toast.LENGTH_LONG);
        } else if (id == R.id.nav_slideshow) {


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

        //TODO afficher list

        list = (ListView) findViewById(R.id.list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(PageNav.this,
                android.R.layout.simple_list_item_1, prenoms);
        list.setAdapter(adapter);

    }

    public void rencontre(View view){
        Button r = (Button) findViewById(R.id.renc);
        r.setTextColor(Color.WHITE);
        r.setBackgroundColor(Color.BLACK);

        Button p = (Button) findViewById(R.id.prog);
        p.setTextColor(Color.BLACK);
        p.setBackgroundColor(Color.LTGRAY);

        //TODO afficher list

         list= (ListView) findViewById(R.id.list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(PageNav.this,
                android.R.layout.simple_list_item_1, noms);
        list.setAdapter(adapter);
    }

    public void afficherForm(View view){
        Intent i=new Intent(this,PageCreerEvent.class);
        startActivity(i);
    }
}

