package com.example.martinez.radio;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioButton;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {
    private EditText eta,etb;
    private TextView tv3;
    private RadioButton r1,r2,r3,r4;
    double ope = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eta = (EditText)findViewById(R.id.input_a);
        etb = (EditText) findViewById((R.id.input_b));
        tv3 =   (TextView) findViewById(R.id.TextR);
        r1 = (RadioButton) findViewById(R.id.radioButton1);
        r2 = (RadioButton) findViewById(R.id.radioButton2);
        r3 = (RadioButton) findViewById(R.id.radioButton3);
        r4 = (RadioButton) findViewById(R.id.radioButton4);
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
    //Este método se ejecutará cuando se presione el botón
    public void operar(View view) {
        String valor1=eta.getText().toString();
        String valor2=etb.getText().toString();
        int nro1=Integer.parseInt(valor1);
        int nro2=Integer.parseInt(valor2);
        int operacion = 0;
        if (r1.isChecked()==true) {
            operacion=nro1+nro2;
        } else if (r2.isChecked()==true) {
            operacion=nro1-nro2;
        }else if (r3.isChecked()==true) {
            operacion=nro1*nro2;
        } else if (r4.isChecked()==true) {
            operacion=nro1/nro2;
        }
        String resu=String.valueOf(operacion);
        tv3.setText(resu);

    }
}
