package fr.univ_lille1.iutinfo.sportin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;

import fr.univ_lille1.iutinfo.communication.ConnexionServ;
import fr.univ_lille1.iutinfo.metier.UserDto;

/**
 * Created by martele on 22/03/18.
 */

public class PageLogin extends AppCompatActivity {

    private static String TAG = PageLogin.class.getSimpleName();

    // private Toast txtResponse;

    //private String jsonResponse;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_login);
        Intent connect = new Intent(this, ConnexionServ.class);
        startActivity(connect);
    }

    public void popUp(View view) {
        EditText textLogin = (EditText) findViewById(R.id.mail);
        EditText textPass = (EditText) findViewById(R.id.mdp);

        ConnexionServ con = ConnexionServ.getInstance();
        UserDto user = con.getUser(textLogin.getText().toString(), textPass.getText().toString());
        Toast.makeText(getApplicationContext(),user.toString(),Toast.LENGTH_LONG).show();

        /* il faut attendre que le user soit init avant de l'utiliser */
        if (user != null && !user.getNom().isEmpty()){//textPass.getText().equals(user.getPassword())) {
            Toast.makeText(getApplicationContext(),user.toString(),Toast.LENGTH_LONG).show();
            /*Intent acceuil = new Intent(this, PageNav.class);
            startActivity(acceuil);*/

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Erreur de connexion");
            builder.setMessage("Votre établissement n'est pas encore partenaire de SportIn. Contactez nous pour utiliser l'app :)");
            builder.setPositiveButton("Contactez nous",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                    "mailto", "contact@sportin.link", null));
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Erreur de connexion à SportIn");
                            intent.putExtra(Intent.EXTRA_TEXT, "Bonjour, votre app ne marche pas. La bise :p");
                            startActivity(Intent.createChooser(intent, "Choisissez une application pour envoyer votre mail :"));

                        }
                    });
            builder.setNegativeButton("Retour", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

    public void onNav(View view) {
        Intent i = new Intent(this, PageNav.class);
        startActivity(i);
    }

}
