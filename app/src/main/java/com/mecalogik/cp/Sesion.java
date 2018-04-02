package com.mecalogik.cp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sesion extends AppCompatActivity {

    EditText cotraseña_sesion;
    Button entrar_sesion;

    String password, password1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);

        SharedPreferences settings = getSharedPreferences("PREFS",0);
        password = settings.getString("password","");
        password1 = settings.getString("password","");


        cotraseña_sesion = (EditText) findViewById(R.id.etcontraseña_sesion);

        entrar_sesion = (Button) findViewById(R.id.btn_sesio);
        entrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = cotraseña_sesion.getText().toString();

                if(text.equals(password)){
                    Intent intent = new Intent(getApplicationContext(),Control.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(Sesion.this,"Contraseña incorrecta",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
