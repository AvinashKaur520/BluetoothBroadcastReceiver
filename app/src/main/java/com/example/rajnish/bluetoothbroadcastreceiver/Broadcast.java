package com.example.rajnish.bluetoothbroadcastreceiver;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Broadcast extends BroadcastReceiver
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
    }
}
