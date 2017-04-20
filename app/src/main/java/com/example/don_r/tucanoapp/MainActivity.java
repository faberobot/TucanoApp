package com.example.don_r.tucanoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    String username, correo;
    Button bSimulador, bVuelo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(this, "Oncreate", Toast.LENGTH_SHORT).show();

        prefs= getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        editor= prefs.edit();

        Bundle extras= getIntent().getExtras(); //permite almacenar informacion usando este formato
        username=extras.getString("username");
        correo=extras.getString("correo");
        bSimulador = (Button) findViewById(R.id.bSimulador);
        bVuelo = (Button) findViewById(R.id.bVuelo);

        bSimulador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentS= new Intent(MainActivity.this, Splash_Simulador.class);
                startActivity(intentS);
            }
        });

        bVuelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentV= new Intent(MainActivity.this, Splash_Vuelo.class);
                startActivity(intentV);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //Para asociar cual es el menu que se le va a poner
        getMenuInflater().inflate(R.menu.menu, menu);//se esta inflando el menu
        return true;//con traer el recurso de menu es suficiente para poder usar los menus de overflow
    }

    //**************PONER PUBLICIDAD*****************************************
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        switch (id){//cuando se presione cerrar sesion debe ir a login activity
            case R.id.mCerrar:
                editor.putInt("login",-1);
                editor.commit();
                Intent intentL= new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentL);
                finish();
                break;
            case R.id.mPerfil: //cuando se presione perfil debe ir a perfil activity
                Intent intentP= new Intent(MainActivity.this, PerfilActivity.class);
                intentP.putExtra("username", username);
                intentP.putExtra("correo", correo);
                startActivity(intentP);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
