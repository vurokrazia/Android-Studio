package com.example.martinez.developers_2;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by martinez on 15/04/15.
 */
public class cursos extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_cursos,container,false);
        return rootView;
    }
    public void run(View view){
        layout_cursos_html lch = new layout_cursos_html();
        lch.vista_cursos(view);
    }
}
