package com.mecalogik.cp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Control extends AppCompatActivity {

    Button consultar_control, guardar_control, editar_control;
    EditText etnombre_control, etid_control, etcantidad_control, etinfor_control;
    TextView etpdf_cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        etid_control = (EditText) findViewById(R.id.ettid_control);
        etnombre_control = (EditText) findViewById(R.id.ettnombre_control);
        etcantidad_control = (EditText) findViewById(R.id.ettcantidad_control);
        etinfor_control =(EditText) findViewById(R.id.ettinformacion_control);
        etpdf_cont = (TextView) findViewById(R.id.ettpdf_control);


        consultar_control = (Button) findViewById(R.id.btnconsultar_control);
        guardar_control = (Button) findViewById(R.id.btnguar_control);
        editar_control = (Button) findViewById(R.id.btnedi_control);

        consultar_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ConsultarDatos().execute("http://192.168.6.69/escuelak/regi.php?id="+etid_control.getText().toString());

            }
        });

        guardar_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CargarDatos().execute("http://172.16.16.65/escuelak/Register.php?id="+etid_control.getText().toString()+"&nombre="+etnombre_control.getText().toString()+"&cantidad="+etcantidad_control.getText().toString()+"&informacion="+etinfor_control.getText().toString()+"&pdf="+etpdf_cont.getText().toString());
            }
        });

        editar_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new EditarDatos().execute("http://172.16.16.65/escuelak/actualizar.php?id="+etid_control.getText().toString()+"&nombre="+etnombre_control.getText().toString()+"&cantidad="+etcantidad_control.getText().toString()+"&informacion="+etinfor_control.getText().toString()+"&pdf="+etpdf_cont.getText().toString());

            }
        });

    }

    private class CargarDatos extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getApplicationContext(), "Se almacenaron los datos correctamente", Toast.LENGTH_LONG).show();

        }
    }

    private class EditarDatos extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getApplicationContext(), "Datos Editados correctamente", Toast.LENGTH_LONG).show();

        }
    }


    private class ConsultarDatos extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            JSONArray ja = null;
            try {
                ja = new JSONArray(result);
                etnombre_control.setText(ja.getString(1));
                etcantidad_control.setText(ja.getString(2));
                etinfor_control.setText(ja.getString(3));
                etpdf_cont.setText(ja.getString(4));

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    private String downloadUrl(String myurl) throws IOException{
        Log.i("URL",""+myurl);
        myurl = myurl.replace(" ","%20");
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("respuesta", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
}