package fr.univ_lille1.iutinfo.communication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
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

import fr.univ_lille1.iutinfo.metier.UserDto;

/**
 * Created by hainguef on 23/03/18.
 */

public class ConnexionServ extends AppCompatActivity {

    public static String addresseServer = "http://172.16.139.1:8080";

    public static final String TAG = ConnexionServ.class.getSimpleName();

    private RequestQueue mRequestQueue;

    private static ConnexionServ mInstance;

    private UserDto user = new UserDto();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInstance = this;
        finish();
    }

    public UserDto getUser(String login, String password){
        //UserDto user = new UserDto();

        /*
        TODO ERREUR ICI DEBUG
        TODO ON PASSE PAS PART LE TRY CATCH




         */
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                ConnexionServ.addresseServer+"/v1/user/"+login, null, new Response.Listener<JSONObject>() {

            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                try {
                    Toast.makeText(getApplicationContext(), "debut init", Toast.LENGTH_LONG).show();
                    user = UserDto.initUserDao(response);
                    System.out.println(user.toString());
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
        System.out.println("-----------------------------\n"+user+"----------------------------------");
        //Toast.makeText(getApplicationContext(), "fin de methode", Toast.LENGTH_LONG).show();

        return this.user;
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
