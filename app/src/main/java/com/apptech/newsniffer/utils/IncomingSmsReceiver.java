package com.apptech.newsniffer.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import com.apptech.newsniffer.view.MainActivity;
import com.apptech.newsniffer.view.ResultFragment;

/**
 * Created by Emmanuel Rop on 14/12/2017.
 */

public class IncomingSmsReceiver extends BroadcastReceiver {
    final SmsManager sms = SmsManager.getDefault();
    Context context;
    String keywords[] = { "Confirmed.","You","have","received","Ksh", "New","M-PESA","balance"};
    String mpesa  = "MPESA";
    CharSequence part1 = "Confirmed. You have received";
    CharSequence part2 = "New M-PESA balance";
    // CharSequence key1 = "Confirmed. You have received Ksh";
    public static String KEYWORD_MPESA = "MPESA";
    String sender = null, message = null;
    int status = 0;

    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        Log.i("SMS Received", "Step 1");

        try {

            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                Log.i("SMS Received", "Bundle not NULL");
                for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage currentMessage = SmsMessage
                            .createFromPdu((byte[]) pdusObj[i]);
                    sender = currentMessage.getDisplayOriginatingAddress();
                    message = currentMessage.getMessageBody();

                    Log.i("SMS Received", "Sender:" + sender + " Message:"
                            + message);
                    status = sanitizeMsg(message, sender);
//					Toast.makeText(context, "Status:"+status+" Sender:"+sender, Toast.LENGTH_LONG).show();
                    if (status == 1||status == 0) {
                        Intent intent1 = new Intent(context, MainActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent1.putExtra("status", status);
                        intent1.putExtra("message", message);
                        intent1.putExtra("sender", sender);
                        context.startActivity(intent1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int sanitizeMsg(String msg, String sender) {
        if (msg.contains(keywords[0])
                &&msg.contains(keywords[1])
                &&msg.contains(keywords[2])
                &&msg.contains(keywords[3])
                &&msg.contains(keywords[4])
                &&msg.contains(keywords[5])
                &&msg.contains(keywords[6])
                &&msg.contains(keywords[7])) {
            // Log.i("Keyword 1", keywords[0]);
            // if (msg.contains(key1)) {
            Log.i("SMS Receive", "*Sanitizing");
            if (sender.contains(KEYWORD_MPESA))
                return 1;
            else
                return 0;
        } else
            return 2;
    }


}
