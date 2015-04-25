package com.example.enviodatos;

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
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListadoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listado);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.listado, menu);
		return true;
	}
	
	public void cargaListado(ArrayList<String> datos){
		ArrayAdapter<String> adaptador =
			new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
		ListView listado = (ListView) findViewById(R.id.listView1);
		listado.setAdapter(adaptador);
	}
	
	public String leer(){
		HttpClient cliente =new DefaultHttpClient();
		HttpContext contexto = new BasicHttpContext();
		HttpGet httpget = new HttpGet("http://10.211.55.3:8080/Android/getData.php");
		String resultado=null;
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
				texto = json.getJSONObject(i).getString("nombre") +" - "+
						json.getJSONObject(i).getString("apellido") +" - "+
						json.getJSONObject(i).getString("edad") +" - "+
						json.getJSONObject(i).getString("modo");
				listado.add(texto);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listado;
	}

}