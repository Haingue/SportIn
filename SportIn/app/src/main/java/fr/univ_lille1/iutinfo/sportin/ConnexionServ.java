package fr.univ_lille1.iutinfo.sportin;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hainguef on 23/03/18.
 */

public class ConnexionServ extends AppCompatActivity {

    public static String addresseServer = "http://172.18.49.6:8080";

    public static final String TAG = ConnexionServ.class.getSimpleName();

    private RequestQueue mRequestQueue;

    private static ConnexionServ mInstance;

    private String jsonResponse;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInstance = this;
        finish();
    }

    public String getUser(){

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
                    jsonResponse += "uno: "+uno+"\n\n";
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
        return jsonResponse;
    }

    public static synchronized ConnexionServ getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
