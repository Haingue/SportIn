package fr.univ_lille1.iutinfo.sportin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import fr.univ_lille1.iutinfo.communication.ConnexionServ;

/**
 * Created by magnierb on 23/03/18.
 */

public class PageCreerEvent extends AppCompatActivity {

    public ConnexionServ con = ConnexionServ.getInstance();
    String pass="toto:toto";



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_creer_event);
    }

    public void creerEvent(View view){

        if(true) { //TODO verifier que l'event est valide

            //TODO creer l'event


            String url = "http://172.18.49.6:8080/v1/events";
            Map<String, String> params = new HashMap<>();
            
            params.put("label", "elodie");
            params.put("dateEvent", "2010-05-05T10:00");
            params.put("price", "10");
            params.put("participants", "10");
            JSONObject parameters = new JSONObject(params);
            RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());


            //adding parameters to the request
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //TODO: handle success
                    Log.d("TAG", response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    //TODO: handle failure
                    Log.e("TAG", error.getMessage(), error);
                }
            }) {

                public Map<String, String> getHeaders(){
                    Map<String, String>  headers = new HashMap<String, String>();
                    String credentials = "toto:toto";
                    String auth = "Basic "+ Base64.encodeToString(credentials.getBytes(),Base64.NO_WRAP);
                    headers.put("Accept", "application/json");
                    headers.put("Authorization", auth);
                    return headers;
                }

            };


            // Add the request to the RequestQueue.
            mQueue.add(jsonRequest);



            Intent i = new Intent(this, PageNav.class);
            startActivity(i);
        } else {

        }

    }


}
