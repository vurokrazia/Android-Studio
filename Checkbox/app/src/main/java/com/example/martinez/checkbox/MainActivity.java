package com.example.martinez.checkbox;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.*;
import android.widget.*;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {
    private EditText eta,etb;
    private TextView mostrart;
    private CheckBox cha,chb,chc,chd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cha = (CheckBox) findViewById(R.id.checkBox);
        chb= (CheckBox) findViewById(R.id.checkBox2);
        chc = (CheckBox) findViewById(R.id.checkBox3);
        chd = (CheckBox) findViewById(R.id.checkBox4);
        eta = (EditText) findViewById(R.id.editText);
        etb = (EditText) findViewById(R.id.editText2);
        mostrart = (TextView) findViewById(R.id.textView3);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void operar(View view){
        String a = eta.getText().toString();
        String b = etb.getText().toString();
        int valor_a = Integer.parseInt(a);
        int valor_b = Integer.parseInt(b);
        String show = "";
        int operacion = 0;
        if(cha.isChecked()==true){
            operacion = valor_a+valor_b;
        }else if(chb.isChecked()==true){
            operacion = valor_a-valor_b;
        }else if(chc.isChecked()==true){
            operacion = valor_a/valor_b;
        }else if(chd.isChecked()==true){
            operacion = valor_a*valor_b;
        }
        show = String.valueOf(operacion);
        mostrart.setText(show);
    }

}
