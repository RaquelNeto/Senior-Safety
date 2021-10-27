package pt.ipp.estg.seniorsafety.DetetorQuedas;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import pt.ipp.estg.seniorsafety.R;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.text.format.Time;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class Verification extends Activity {

    private Button buttonYes;
    private Button buttonNo;

    private final String contactFileName = "contact.txt";

    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        buttonYes = (Button) findViewById(R.id.buttonYes);
        buttonNo = (Button) findViewById(R.id.buttonNo);

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Verification.this, MainActivity.class));
            }
        });


        if(checkPermission(Manifest.permission.SEND_SMS)){
            buttonNo.setEnabled(true);
            finish();
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onBackPressed() {
        // Swallow all back button presses
    }

    //ler contact
    private Contact loadContact() {
        Contact contact = new Contact();

        InputStream in = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            in = new BufferedInputStream(openFileInput(this.contactFileName));
            isr = new InputStreamReader(in);
            br = new BufferedReader(isr);

            contact.name = br.readLine();
            contact.cell = br.readLine();
            contact.email = br.readLine();

            br.close();
            isr.close();
            in.close();
        } catch (FileNotFoundException e) {
            //acontecerá até que o usuário crie seu contato de emergência pela primeira vez
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return contact;
    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    public void sendAndCall(View view) {
        Contact c = loadContact();

        if(checkPermission(Manifest.permission.SEND_SMS) && c!= null) {
            String number = c.cell;

            SmsManager man = SmsManager.getDefault();
            man.sendTextMessage(number, null, " Eu caí e aleijei-me. Por favor ajude-me.", null, null);
            Toast.makeText(getApplicationContext(), "SMS enviada.",Toast.LENGTH_LONG).show();

            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + c.cell));

            finish();
        } else {
            Toast.makeText(getApplicationContext(),"SMS não enviada, por favor tente de novo.", Toast.LENGTH_LONG).show();
        }

    }



}
