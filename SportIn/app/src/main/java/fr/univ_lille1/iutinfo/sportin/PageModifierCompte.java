package fr.univ_lille1.iutinfo.sportin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Page pour modifier le compte de l'utilisateur
 * Created by parissej on 27/03/18.
 */

public class PageModifierCompte extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifier_compte);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //TODO FAIRE LE XML modifier_compte

    /**
     * Méthode liée au bouton "Retour"
     * qui doit envoyer vers la page de profil
     * @param view
     */
    public void retour(View view) {
        Intent i=new Intent(this,PageProfil.class);
        startActivity(i);
    }

}
