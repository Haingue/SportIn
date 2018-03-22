package fr.univ_lille1.iutinfo.sportin;

/**
 * Created by magnierb on 22/03/18.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class PageGaucheFragment extends Fragment{
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //System.out.print(R.layout.page_gauche_layout.);
        return inflater.inflate(R.layout.page_gauche_layout, container, false);
    }
}