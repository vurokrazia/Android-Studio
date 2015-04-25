package com.example.martinez.developers_app;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;


public class Usuarios extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_usuarios, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        RelativeLayout main_view = (RelativeLayout) findViewById(R.id.main);
        switch (item.getItemId()){
            case R.id.Red:
                if(item.isChecked())
                    item.setChecked(false);
                else if(item.isChecked())
                    item.setChecked(true);
                main_view.setBackgroundColor(Color.RED);
                return true;
            case R.id.Yellow:
                if(item.isChecked())
                    item.setChecked(false);
                else if(item.isChecked())
                    item.setChecked(true);
                main_view.setBackgroundColor(Color.YELLOW);
                return true;
            case R.id.yolo:
                if(item.isChecked())
                    item.setChecked(false);
                else if(item.isChecked())
                    item.setChecked(true);
                main_view.setBackgroundColor(Color.MAGENTA);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
