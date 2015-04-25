package com.example.martinez.developers_app;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.sql.SQLData;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_a = (Button) findViewById(R.id.button);
        Button btn_b = (Button) findViewById(R.id.button2);
        Button btn_c = (Button) findViewById(R.id.button3);

        btn_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), inicio_sesion.class);
                startActivity(i);
            }
        });
        btn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Acerca_de.class);
                startActivity(i);
            }
        });
        btn_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
