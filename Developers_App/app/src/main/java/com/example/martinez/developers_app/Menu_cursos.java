package com.example.martinez.developers_app;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

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


public class Menu_cursos extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cursos);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        go();
        //botonera();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_cursos, menu);
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
    /*public void botonera(){
        final Button button_a = (Button) findViewById(R.id.button5),
                button_b = (Button) findViewById(R.id.button6),
                button_c = (Button) findViewById(R.id.button7);
        button_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Curso.class);
                String titulo_boton = button_a.getText().toString();
                i.putExtra("titulo", titulo_boton);
                startActivity(i);
            }
        });
        button_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Curso.class);
                String titulo_boton = button_b.getText().toString();
                i.putExtra("titulo", titulo_boton);
                startActivity(i);
            }
        });
        button_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Curso.class);
                String titulo_boton = button_c.getText().toString();
                i.putExtra("titulo", titulo_boton);
                startActivity(i);
            }
        });
    }*/
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
    String cursos []= null, titulo [] = null;
    public void cargaListado(ArrayList<String> datos){
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        ListView listado = (ListView) findViewById(R.id.lista_cursos_disponibles);
        listado.setAdapter(adaptador);
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), Curso.class);
                i.putExtra("titulo", titulo[position]);
                i.putExtra("id", cursos[position]);
                startActivity(i);
            }
        });

    }

    public String leer(){
        Log.i("Activado: ", "Cursos Disponibles");
        HttpClient cliente =new DefaultHttpClient();
        HttpContext contexto = new BasicHttpContext();
        HttpGet httpget = new HttpGet("http://developersmultiplafom.esy.es/android/cursos_disponibles.php");
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
            cursos = new String[json.length()];
            titulo = new String[json.length()];
            for (int i=0; i<json.length();i++){
                texto = json.getJSONObject(i).getString("c_id") + "\t" +
                        json.getJSONObject(i).getString("c_nombre");
                cursos [i] = json.getJSONObject(i).getString("c_id");
                titulo [i] = json.getJSONObject(i).getString("c_nombre");
                /* Usando MYSQL
                texto = z +" - " +
                        json.getJSONObject(i).getString("publicaciones_titulo") + " - " +
                        json.getJSONObject(i).getString("publicaciones_fecha_creacion");
                        cursos [i] = json.getJSONObject(i).getString("publicaciones_titulo");*/
                listado.add(texto);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return listado;
    }
}