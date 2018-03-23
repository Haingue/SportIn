package fr.univ_lille1.iutinfo.sportin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by martele on 22/03/18.
 */

public class PageLogin extends AppCompatActivity {

    private static String TAG = PageLogin.class.getSimpleName();

   // private Toast txtResponse;

    private String jsonResponse;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_login);
        Intent i = new Intent(this, ConnexionServ.class);
        startActivity(i);
    }

    public void onValider(View view){

        getUser();

    }

    public void getUser(){
        final JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                ConnexionServ.addresseServer+"/v1/tempo/user", null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    int uno = response.getInt("uno");
                    String login = response.getString("login");
                    String pass = response.getString("pass");
                    String nom = response.getString("nom");
                    String prenom = response.getString("prenom");
                    String fonction = response.getString("fonction");
                    String corp = response.getString("corp");

                    jsonResponse = "";
                    jsonResponse += "uno: "+uno;
                    jsonResponse += "Nom: " + nom + "\n\n";
                    jsonResponse += "Prenom: " + prenom + "\n\n";
                    jsonResponse += "Email: " + login + "\n\n";
                    jsonResponse += "Password: " + pass + "\n\n";
                    jsonResponse += "Entreprise: " + corp + "\n\n";
                    jsonResponse += "Fonction: " + fonction+ "\n\n";

                    System.out.println(jsonResponse);
                    //txtResponse.setText(jsonResponse);
                    Toast.makeText(getApplicationContext(), jsonResponse, Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
            }
        });

        // Adding request to request queue
        ConnexionServ c = ConnexionServ.getInstance();
        if(c != null) {
            c.addToRequestQueue(jsonObjReq);
        }else{
            System.out.println("-------------------------------\nNull\n----------------------------------------\n");
        }
    }

}
