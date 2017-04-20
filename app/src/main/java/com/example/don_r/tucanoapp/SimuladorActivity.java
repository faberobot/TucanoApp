package com.example.don_r.tucanoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SimuladorActivity extends AppCompatActivity {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    String username, correo;
    Button bLibre, bTutorial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulador);

        prefs= getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        editor= prefs.edit();

        bLibre = (Button) findViewById(R.id.bLibre);
        bTutorial = (Button) findViewById(R.id.bTutorial);

        bLibre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentL= new Intent(SimuladorActivity.this, VueloLibreActivity.class);
                startActivity(intentL);
            }
        });

        bTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentT= new Intent(SimuladorActivity.this, TutorialVueloActivity.class);
                startActivity(intentT);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) { //Para asociar cual es el menu que se le va a poner
        getMenuInflater().inflate(R.menu.menug, menu);//se esta inflando el menu
        return true;//con traer el recurso de menu es suficiente para poder usar los menus de overflow
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        switch (id){//cuando se presione cerrar sesion debe ir a login activity
            case R.id.mCerrar:
                editor.putInt("login",-1);
                editor.commit();
                Intent intentL= new Intent(SimuladorActivity.this, LoginActivity.class);
                startActivity(intentL);
                finish();
                break;
            case R.id.mPerfil: //cuando se presione perfil debe ir a perfil activity
                Intent intentP= new Intent(SimuladorActivity.this, PerfilActivity.class);
                intentP.putExtra("username", username);
                intentP.putExtra("correo", correo);
                startActivity(intentP);
                break;
            case R.id.mMainActivity:
                Intent intentM = new Intent(SimuladorActivity.this,MainActivity.class);
                intentM.putExtra("username", username);
                intentM.putExtra("correo", correo);
                startActivity(intentM);
        }
        return super.onOptionsItemSelected(item);
    }
}
