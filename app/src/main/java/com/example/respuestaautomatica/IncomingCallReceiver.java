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
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            intent.getExtras().keySet().forEach(k -> {
//                Log.d(TAG, k);
//                Log.d(TAG, intent.getExtras().get(k).toString());
//            });
//        }

        SharedPreferences sharedPref = context.getSharedPreferences( "hola", context.MODE_PRIVATE);
        String mensaje = sharedPref.getString("hola", "TEST");

        Log.d(TAG, "Broadcast recibido " + mensaje);
        if(intent.hasExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)){
            String numero = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Log.d(TAG, numero);
        }
        String ultimoEstado = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        Log.d(TAG, ultimoEstado);
//        TelephonyManager tm = (TelephonyManager)mContext.getSystemService(Context.TELEPHONY_SERVICE);
//        int events = PhoneStateListener.LISTEN_CALL_STATE;
//
//        tm.listen(phoneStateListener, events);
        SmsManager smsManager =     SmsManager.getDefault();
        smsManager.sendTextMessage("4451129323", null, "I'm busy bruh", null, null);
//        TelephonyManager tm = (TelephonyManager)mContext.getSystemService(Context.TELEPHONY_SERVICE);
//        tm.listen(phoneStateListener, PhoneStateListener.LISTEN_NONE);
    }

    private final PhoneStateListener phoneStateListener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);

            Log.d(TAG, "State: "+state);
            Log.i(TAG, "number: *" + incomingNumber + "*");
            Toast.makeText(mContext,"Incoming Call: " + incomingNumber + "",Toast.LENGTH_LONG).show();

//            if(incomingNumber != null && incomingNumber.length()>0){
//                switch (state) {
//                    case TelephonyManager.CALL_STATE_RINGING:
//
//                        SmsManager smsManager =     SmsManager.getDefault();
//                        smsManager.sendTextMessage(incomingNumber, null, "I'm busy bruh", null, null);
//                        TelephonyManager tm = (TelephonyManager)mContext.getSystemService(Context.TELEPHONY_SERVICE);
//                        tm.listen(phoneStateListener, PhoneStateListener.LISTEN_NONE);
//
//                }
//            }



        }
    };
}
