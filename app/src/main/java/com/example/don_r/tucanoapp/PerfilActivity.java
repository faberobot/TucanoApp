package com.example.don_r.tucanoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class PerfilActivity extends AppCompatActivity {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    String username, correo;
    TextView textUsuario, textCorreo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        prefs= getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        editor= prefs.edit();

        textUsuario= (TextView) findViewById(R.id.textUsuario);
        textCorreo= (TextView) findViewById(R.id.textCorreo);

        Bundle extras= getIntent().getExtras(); //permite almacenar informacion usando este formato
        username=extras.getString("username");
        correo=extras.getString("correo");

        textUsuario.setText(username);
        textCorreo.setText(correo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //Para asociar cual es el menu que se le va a poner
        getMenuInflater().inflate(R.menu.menup, menu);//se esta inflando el menu
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
                Intent intentL= new Intent(PerfilActivity.this, LoginActivity.class);
                startActivity(intentL);
                finish();
                break;
            case R.id.mMainActivity: //cuando se presione perfil debe ir a perfil activity
                Intent intentM= new Intent(PerfilActivity.this, MainActivity.class);
                intentM.putExtra("username", username);
                intentM.putExtra("correo", correo);
                startActivity(intentM);
                //finish();
                break;
            //default:
            //  Intent intentD= new Intent(PerfilActivity.this, LoginActivity.class);
            //startActivity(intentD);
            //finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
