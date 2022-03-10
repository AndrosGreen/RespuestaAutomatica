package com.example.respuestaautomatica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    Button btnGuardar;
    EditText txtNumero;
    EditText txtMensaje;
    Context context = this;


    private AudioManager am;
    int buttonState = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnGuardar = findViewById(R.id.btnGuardar);
        txtMensaje = findViewById(R.id.txtMensaje);
        txtNumero = findViewById(R.id.txtNumero);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttonState == 0){
                    buttonState = 1;

                    Toast.makeText(getApplicationContext(), "Go!!",
                            Toast.LENGTH_SHORT).show();
                    Log.e("testt", "hola");
//                    Intent intent = new Intent();
//                    intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
//                    intent.setAction("android.intent.action.Trigger");
//                    sendBroadcast(intent);=
                    IntentFilter filter = new IntentFilter();
                    filter.addAction("android.intent.action.PHONE_STATE");
                    registerReceiver(new IncomingCallReceiver(), filter);
                }
                else {
                    buttonState = 0;
                    Toast.makeText(getApplicationContext(), "Come back!!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = MainActivity.this.getSharedPreferences("hola",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("hola", "4451239516");
                editor.apply();
                editor.commit();
            }
        });
    }


}