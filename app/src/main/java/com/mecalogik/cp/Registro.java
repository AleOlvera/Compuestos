package com.mecalogik.cp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    EditText contraseña, contraseña2;
    Button listo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        contraseña = (EditText) findViewById(R.id.etcontraseña);
        contraseña2 = (EditText) findViewById(R.id.etcontraseña2);
        listo = (Button) findViewById(R.id.btnlisto);

        listo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contraseña11 = (contraseña).getText().toString();
                String contraseña22 = (contraseña2).getText().toString();

                if (contraseña11.equals("") || contraseña22.equals("")){

                    Toast.makeText(Registro.this,"No se ingresó contraseña",Toast.LENGTH_SHORT).show();
                } else {
                    if (contraseña11.equals(contraseña22)){
                        SharedPreferences settings = getSharedPreferences("PREFS",0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("password",contraseña11);
                        editor.apply();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

                    }else {
                        Toast.makeText(Registro.this,"La contraseña no coincide",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}