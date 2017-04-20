package com.example.don_r.tucanoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ConfiguracionActivity extends AppCompatActivity {

    String username, correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
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
                Intent intentL= new Intent(ConfiguracionActivity.this, LoginActivity.class);
                startActivity(intentL);
                finish();
                break;
            case R.id.mPerfil: //cuando se presione perfil debe ir a perfil activity
                Intent intentP= new Intent(ConfiguracionActivity.this, PerfilActivity.class);
                intentP.putExtra("username", username);
                intentP.putExtra("correo", correo);
                startActivity(intentP);
                break;
            case R.id.mMainActivity:
                Intent intentM = new Intent(ConfiguracionActivity.this,MainActivity.class);
                intentM.putExtra("username", username);
                intentM.putExtra("correo", correo);
                startActivity(intentM);
        }
        return super.onOptionsItemSelected(item);
    }
}
