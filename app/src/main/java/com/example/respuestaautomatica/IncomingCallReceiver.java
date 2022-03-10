package com.example.respuestaautomatica;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class IncomingCallReceiver extends BroadcastReceiver{
    private static final String TAG = "MyListener";
    private Context mContext;
    private Intent mIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        mIntent = intent;

        SharedPreferences sharedPref = context.getSharedPreferences( "hola", context.MODE_PRIVATE);
        String mensaje = sharedPref.getString("mensaje", "TEST");
        String telefono = sharedPref.getString("hola", "TEST");

        Log.d(TAG, "Broadcast recibido " + mensaje);
        String numero = "";
        if(intent.hasExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)){
            numero = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Log.d(TAG, numero);
        }
        String ultimoEstado = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        Log.d(TAG, ultimoEstado);

        Toast.makeText(mContext,"Incoming Call: " + telefono + "",Toast.LENGTH_LONG).show();

        if( telefono.equals(numero) ){
            SmsManager smsManager =     SmsManager.getDefault();
            smsManager.sendTextMessage(telefono, null, mensaje, null, null);
        }

    }

}
