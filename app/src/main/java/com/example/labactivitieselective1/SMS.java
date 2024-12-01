package com.example.labactivitieselective1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.Manifest;

public class SMS extends AppCompatActivity {
    Button sendSMS, sendBSMS, call;
    EditText phoneNo, message;
    ProgressDialog progressDialog;
    Intent smsIntent, callIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sms);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        sendMessage();
        sendMessageBuiltIn();
        phoneCall();
    }

    public void init(){
        progressDialog = new ProgressDialog(this);
        sendSMS = findViewById(R.id.btnSMS);
        sendBSMS = findViewById(R.id.btnBSMS);
        call = findViewById(R.id.btnPhoneCall);
        phoneNo = findViewById(R.id.etPhoneNo);
        message = findViewById(R.id.etSMS);
    }
    public void sendMessage(){
        sendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Manage SMS operations such as sending data, text, and pdu SMS messages.
                    // Get this object by calling the static method getDefault().
                    SmsManager smsManager = SmsManager.getDefault();
                    // Note: using this method requires that your app has the SEND_SMS permission.
                    smsManager.sendTextMessage(phoneNo.getText().toString(), // the address to send the message
                            null, // is the service center address or null to use the current default SMSC.
                            message.getText().toString(), // the body of the message to send.
                            null, // if not null this PendingIntent is broadcast when the message is successfully sent, or failed.
                            null); // if not null this PendingIntent is broadcast when the message is delivered to the recipient.

                    // The code below show the Progress Dialog and Cancel it after 3 seconds.
                    progressDialog.setTitle("Sending...");
                    progressDialog.setMessage("Message Sent!");
                    progressDialog.show();

                    Runnable progressRunnable = new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.cancel();
                        }
                    };

                    Handler pdCanceller = new Handler();
                    pdCanceller.postDelayed(progressRunnable,3000);

                }catch (Exception e){
                    progressDialog.setTitle("Sending...");
                    progressDialog.setMessage("Message was not delivered!");
                    progressDialog.show();

                    Runnable progressRunnable = new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.cancel();
                        }
                    };

                    Handler pdCanceller = new Handler();
                    pdCanceller.postDelayed(progressRunnable,3000);
                }
            }
        });
    }
    public void sendMessageBuiltIn(){
        sendBSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Send a message to someone specified by the data.
                smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNo.getText().toString()));
                // Add extended data to intent.
                smsIntent.putExtra("sms_body",message.getText().toString());
                // Start smsIntent
                startActivity(smsIntent);
            }
        });
    }
    public void phoneCall(){
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Perform a call to someone specified by the data.
                callIntent = new Intent(Intent.ACTION_CALL,  Uri.parse("tel:"+phoneNo.getText().toString()));
                // This will check the permission and if granted by the user in manifest file for CALL_PHONE
                if (ContextCompat.checkSelfPermission(SMS.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(SMS.this, new String[]
                            {Manifest.permission.CALL_PHONE},1);
                    // The callback method gets the results of the request.
                }else{
                    // You already have permission
                    try {
                        startActivity(callIntent);
                    }catch (SecurityException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
