package fr.univ_lille1.iutinfo.sportin;

/**
 * Created by magnierb on 22/03/18.
 */

import java.util.List;
import java.util.Vector;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

public class FragmentsSliderActivity extends FragmentActivity {

    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.viewpager);

        // Création de la liste de Fragments que fera défiler le PagerAdapter
        List fragments = new Vector();

        // Ajout des Fragments dans la liste
        fragments.add(Fragment.instantiate(this,PageGaucheFragment.class.getName()));
        fragments.add(Fragment.instantiate(this,PageMilieuFragment.class.getName()));
        fragments.add(Fragment.instantiate(this,PageDroiteFragment.class.getName()));

        // Création de l'adapter qui s'occupera de l'affichage de la liste de
        // Fragments
        this.mPagerAdapter = new MyPagerAdapter(super.getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager) super.findViewById(R.id.viewpager);
        // Affectation de l'adapter au ViewPager
        pager.setAdapter(this.mPagerAdapter);
    }

    public void onConnect(View view){
       Intent i = new Intent(FragmentsSliderActivity.this,PageLogin.class);
       startActivity(i);
    }
}
