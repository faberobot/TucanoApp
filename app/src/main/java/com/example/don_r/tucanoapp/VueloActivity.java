package com.example.don_r.tucanoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class VueloActivity extends AppCompatActivity {

    String username, correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vuelo);
    }

    public boolean onCreateOptionsMenu(Menu menu) { //Para asociar cual es el menu que se le va a poner
        getMenuInflater().inflate(R.menu.menuvr, menu);//se esta inflando el menu
        return true;//con traer el recurso de menu es suficiente para poder usar los menus de overflow
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        switch (id){//cuando se presione cerrar sesion debe ir a login activity
            case R.id.mCerrar:
                Intent intentL= new Intent(VueloActivity.this, LoginActivity.class);
                startActivity(intentL);
                finish();
                break;
            case R.id.mPerfil: //cuando se presione perfil debe ir a perfil activity
                Intent intentP= new Intent(VueloActivity.this, PerfilActivity.class);
                intentP.putExtra("username", username);
                intentP.putExtra("correo", correo);
                startActivity(intentP);
                break;
            case R.id.mMainActivity:
                Intent intentM = new Intent(VueloActivity.this,MainActivity.class);
                intentM.putExtra("username", username);
                intentM.putExtra("correo", correo);
                startActivity(intentM);
                break;
            case R.id.mConfiguracion:
                Intent intentC = new Intent(VueloActivity.this,ConfiguracionActivity.class);
                startActivity(intentC);
        }
        return super.onOptionsItemSelected(item);
    }
}
