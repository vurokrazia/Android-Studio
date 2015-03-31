package com.example.martinez.captura_boton;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campo_a=(EditText)findViewById(R.id.campo_a);
        campo_b=(EditText)findViewById(R.id.campo_b);
        resultado=(EditText)findViewById(R.id.resultado);
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
    private EditText campo_a,campo_b;
    private TextView resultado;
    public void sumar(View view) {
        int nro1 = empy_a();
        int nro2 = empy_b();
        int suma =nro1+nro2;
        String resu = String.valueOf(suma);
        resultado.setText(resu);
    }
    public void resta(View view) {
        int nro1 = empy_a();
        int nro2 = empy_b();
        int res = nro1-nro2;
        String resu = String.valueOf(res);
        resultado.setText(resu);
    }
    public void division(View view) {
        int nro1 = empy_a();
        int nro2 = empy_b();
        float divi =nro1/nro2;
        String resu = String.valueOf(divi);
        resultado.setText(resu);
    }
    public void multiplica(View view) {
        int nro1 = empy_a();
        int nro2 = empy_b();
        int mul =nro1*nro2;
        String resu = String.valueOf(mul);
        resultado.setText(resu);
    }
    public int empy_a(){
        String empy = campo_a.getText().toString();
        if(empy.isEmpty()){
            return 0;
        }
        return Integer.parseInt(empy);
    }
    public int empy_b(){
        String empy = campo_b.getText().toString();
        if(empy.isEmpty()){
            return 0;
        }
        return Integer.parseInt(empy);
    }
    public void restaura(View view){
        campo_a.setText("");
        campo_b.setText("");
        resultado.setText("");
    }
}
