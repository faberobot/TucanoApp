package com.example.don_r.tucanoapp;

import android.content.Intent;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText eUsuario, eContraseña;
    Button bIniciar;
    TextView tRegistro;
    String username, password, correo;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       /* Bundle extras = getIntent().getExtras();

        username= extras.getString("username");
        password= extras.getString("contraseña");
        correo = extras.getString("correo");*/

        prefs= getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        editor= prefs.edit();

        username = prefs.getString("username", "noname");
        password = prefs.getString("password", "nopass");
        correo = prefs.getString("correo","nocorreo");

        Log.d("login", String.valueOf(prefs.getInt("login",-1)));
        Log.d("username", username);
        Log.d("password", password);
        Log.d("correo", correo);

        if(prefs.getInt("login",-1)==1){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("correo", correo);
            startActivity(intent);
            finish();
        }

        eUsuario=(EditText) findViewById(R.id.eUsuario);
        eContraseña=(EditText) findViewById(R.id.eContraseña);
        bIniciar=(Button) findViewById(R.id.bIniciar);
        tRegistro=(TextView) findViewById(R.id.tRegistro);

        Log.d("login", String.valueOf(prefs.getInt("login",-1)));
        Log.d("username", username);
        Log.d("password", password);
        Log.d("correo", correo);

        bIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //se compara con los datos obtenidos en registro y se va con el intent a otra actividad
                if(eUsuario.getText().toString().equals("") || eContraseña.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Favor ingresar la información requerida",Toast.LENGTH_SHORT).show();
                    return;
                }

                if ((eUsuario.getText().toString().equals(username))&&(eContraseña.getText().toString().equals(password)))
                {
                    editor.putInt("login",1);
                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("correo", correo);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"Usuario o Contraseña no válidos",Toast.LENGTH_SHORT).show();
                    eUsuario.setText("");
                    eContraseña.setText("");
                    return;
                }

            }
        });

        tRegistro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                Intent intent= new Intent(LoginActivity.this, RegistroActivity.class);
                startActivityForResult(intent, 1234);}
                //Toast.makeText(getApplicationContext(),"Favor ingresar la información requerida",Toast.LENGTH_SHORT).show();

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //se comparan las cadenas con los valores de registro
        if(requestCode == 1234 && resultCode == RESULT_OK){
            username = data.getExtras().getString("username");
            password= data.getExtras().getString("password");
            correo= data.getExtras().getString("correo");

            editor.putString("username", username);
            editor.putString("password", password);
            editor.putString("correo", correo);

            Log.d("NombreUsuario",username);


        }else{
            if(requestCode == 1234 && resultCode == RESULT_CANCELED){
                Toast.makeText(this, "ERROR en Registro", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

