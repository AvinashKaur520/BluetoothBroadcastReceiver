package com.example.rajnish.bluetoothbroadcastreceiver;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    private BroadcastReceiver receiver;

    @Override
    protected void onDestroy()
    {
        if(receiver!=null)
        {
            unregisterReceiver(receiver);
        }
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        filter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);

        receiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);  //For state change

                switch(state)
                {
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Toast.makeText(context, "Bluetooth OFF", Toast.LENGTH_SHORT).show();
                        break;

                    case BluetoothAdapter.STATE_TURNING_ON:
                        Toast.makeText(context, "Bluetooth ON", Toast.LENGTH_SHORT).show();
                        break;
                }


                final String action = intent.getAction();

                if(action.equals(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED)) {

                    int mode = intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE, BluetoothAdapter.ERROR);

                    switch(mode){
                        case BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE:
                            Toast.makeText(MainActivity.this, "Pairing completed", Toast.LENGTH_SHORT).show();
                            //when devices paired

                            break;

                        case BluetoothAdapter.SCAN_MODE_CONNECTABLE:
                            Toast.makeText(MainActivity.this, "Request of pairing", Toast.LENGTH_SHORT).show();
                            //when device get pairing request

                            break;
                        //case BluetoothAdapter.SCAN_MODE_NONE:
                           // Toast.makeText(MainActivity.this, "Connection Lost", Toast.LENGTH_SHORT).show();
                            //when bluetooth off

                            //break;
                    }
                }
            }
        };

        registerReceiver(receiver,filter);

    }
}
