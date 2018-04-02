package com.mecalogik.cp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button consultar,registro,sesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        consultar = (Button) findViewById(R.id.btconsultar);
        //registro = (Button) findViewById(R.id.btregistro);
        sesion = (Button) findViewById(R.id.btiniicarsesion);

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent consultar = new Intent(MainActivity.this, Consulta.class);
                startActivity(consultar);
            }
        });

        /*registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registro = new Intent(MainActivity.this,Registro.class);
                startActivity(registro);
            }
        });*/

        sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sesion = new Intent(MainActivity.this,Sesion.class);
                startActivity(sesion);
            }
        });
    }
}


