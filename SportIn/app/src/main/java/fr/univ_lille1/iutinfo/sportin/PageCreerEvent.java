package fr.univ_lille1.iutinfo.sportin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by magnierb on 23/03/18.
 */

public class PageCreerEvent extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_creer_event);
    }

    public void creerEvent(View view){

        if(true) { //TODO verifier que l'event est valide

            //TODO creer l'event

            Toast.makeText(getApplicationContext(),"Event crée",Toast.LENGTH_LONG).show();

            Intent i = new Intent(this, PageNav.class);
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(),"Event invalide",Toast.LENGTH_LONG).show();
        }

    }

}
