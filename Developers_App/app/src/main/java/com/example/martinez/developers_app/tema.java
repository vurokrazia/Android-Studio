package com.example.martinez.developers_app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.w3c.dom.Text;

import java.util.ArrayList;


public class tema extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);
        crear_vista();
    }
    public void crear_vista (){
        Bundle bundle = getIntent().getExtras();
        go();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tema, menu);
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
    public void go(){
        Thread tr = new Thread(){
            @Override
            public void run(){
                final String Resultado = leer();
                runOnUiThread(
                        new Runnable() {

                            @Override
                            public void run() {
                                cargaListado(obtDatosJSON(Resultado));
                            }
                        });
            }
        };
        tr.start();
    }

    public void cargaListado(ArrayList<String> datos){
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        ListView listado = (ListView) findViewById(R.id.lista_tema);
        listado.setAdapter(adaptador);
    }
    public String leer(){
        Bundle bundle = getIntent().getExtras();
        String s_id = bundle.getString("id");
        Log.i("ID: ", s_id);
        HttpClient cliente =new DefaultHttpClient();
        HttpContext contexto = new BasicHttpContext();
        HttpGet httpget = new HttpGet("http://developersmultiplafom.esy.es/android/temario.php?tema=" + s_id);
        String resultado = null;
        try {
            HttpResponse response = cliente.execute(httpget,contexto);
            HttpEntity entity = response.getEntity();
            resultado = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            // TODO: handle exception
        }
        return resultado;
    }
    public ArrayList<String> obtDatosJSON(String response){
        ArrayList<String> listado= new ArrayList<String>();
        try {
            JSONArray json= new JSONArray(response);
            String texto="";
            for (int i=0; i<json.length();i++){
                setTitle("Tema: " + json.getJSONObject(i).getString("tc_titulo"));
                texto = json.getJSONObject(i).getString("tc_cuerpo");
                listado.add(texto);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return listado;
    }
}
