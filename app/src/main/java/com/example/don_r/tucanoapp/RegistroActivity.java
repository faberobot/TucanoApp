package com.example.don_r.tucanoapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    EditText eRUsuario, eRContraseña, eRRepContraseña, eRCorreo;
    Button bRegistrar, bCancelar;
    UsuariosRegistrados usuariosRegistrados;
    SQLiteDatabase dbUsuarios;
    ContentValues dataBD= new ContentValues();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        eRUsuario= (EditText) findViewById(R.id.eRUsuario);
        eRContraseña= (EditText) findViewById(R.id.eRContraseña);
        eRRepContraseña= (EditText) findViewById((R.id.eRRepContraseña));
        eRCorreo= (EditText) findViewById(R.id.eRCorreo);
        bRegistrar= (Button) findViewById(R.id.bRegistrar);
        bCancelar = (Button) findViewById(R.id.bCancelar);

        usuariosRegistrados = new UsuariosRegistrados(this, "dbUsuarios", null,1);
        dbUsuarios=usuariosRegistrados.getWritableDatabase();

        bRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//una vez se de registrar se obtienen los valores y se finaloza la actividad
                //Intent intent= new Intent(RegistroActivity.this, LoginActivity.class);
                if(eRUsuario.getText().toString().equals("") || eRContraseña.getText().toString().equals("") || eRRepContraseña.getText().toString().equals("") || eRCorreo.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Favor ingresar la información requerida",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!eRContraseña.getText().toString().equals(eRRepContraseña.getText().toString()))
                {
                    eRContraseña.setText("");
                    eRRepContraseña.setText("");
                    Toast.makeText(getApplicationContext(),"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent= new Intent();
                intent.putExtra("username", eRUsuario.getText().toString());
                intent.putExtra("password",eRContraseña.getText().toString());
                intent.putExtra("correo", eRCorreo.getText().toString());
                dbUsuarios.execSQL("INSERT INTO Usuarios VALUES(null, '"+eRUsuario.getText().toString()+"', '"+eRCorreo.getText().toString()+"', '"+eRContraseña.getText().toString()+"','"+"Principiante"+"')");
                setResult(RESULT_OK, intent);
                finish();
                //startActivity(intent);
            }
        });

        bCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
    }
}

