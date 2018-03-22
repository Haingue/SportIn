package fr.univ_lille1.iutinfo.sportin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Created by martele on 22/03/18.
 */

public class PageLogin extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set content view AFTER ABOVE sequence (to avoid crash)
        setContentView(R.layout.activity_login);
    }


}
