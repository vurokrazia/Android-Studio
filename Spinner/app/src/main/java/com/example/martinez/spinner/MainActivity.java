package com.example.martinez.spinner;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private Spinner spinner;
    private TextView resultado;
    private EditText campo_a,campo_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campo_a = (EditText) findViewById(R.id.editText);
        campo_b = (EditText) findViewById(R.id.editText2);
        resultado = (TextView) findViewById(R.id.textView3);
        spinner = (Spinner) findViewById(R.id.spinner);
        String []opc ={"SUMA","RESTA","DIVISION","MULTIPLICACION"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opc);
        spinner.setAdapter(adapter);
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
        String valor_a = campo_a.getText().toString();
        String valor_b = campo_b.getText().toString();
        int a = Integer.parseInt(valor_a);
        int b = Integer.parseInt(valor_b);
        String seleccion = spinner.getSelectedItem().toString();
        int res = 0;
        if(seleccion.equals("SUMA")){
            res = a+b;
        }else if(seleccion.equals("RESTA")){
            res = a-b;
        }else if(seleccion.equals("DIVISION")){
            res = a/b;
        }else if(seleccion.equals("MULTIPLICACION")){
            res = a*b;
        }
        String texto = String.valueOf(res);
        resultado.setText(texto);
    }
}
