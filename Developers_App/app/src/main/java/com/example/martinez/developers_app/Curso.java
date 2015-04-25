package com.example.martinez.developers_app;

import android.content.Intent;
import android.graphics.Color;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Curso extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        crear_vista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_curso, menu);
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
    public void crear_vista (){
        Bundle bundle = getIntent().getExtras();
        String s_titulo = bundle.getString("titulo");
        setTitle(s_titulo);
        go();
    }
    String id_tema [] = null;
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
        ListView listado = (ListView) findViewById(R.id.listView);
        listado.setAdapter(adaptador);
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), tema.class);
                i.putExtra("id", id_tema[position]);
                startActivity(i);
            }
        });
    }

    public String leer(){
        Bundle bundle = getIntent().getExtras();
        String s_id = bundle.getString("id");
        HttpClient cliente =new DefaultHttpClient();
        HttpContext contexto = new BasicHttpContext();
        HttpGet httpget = new HttpGet("http://developersmultiplafom.esy.es/android/curso.php?curso=" + s_id);
        //192.168.1.70/
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
            id_tema = new String[json.length()];
            for (int i=0; i<json.length();i++){
                texto = json.getJSONObject(i).getString("t_id") + " " +
                        json.getJSONObject(i).getString("t_nombre");
                id_tema [i]= json.getJSONObject(i).getString("t_id");
                listado.add(texto);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return listado;
    }

}