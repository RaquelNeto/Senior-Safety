package pt.ipp.estg.seniorsafety.medicamentos.alarmeSMS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import pt.ipp.estg.seniorsafety.DetetorQuedas.Contact;
import pt.ipp.estg.seniorsafety.R;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AlarmMedicationSMS extends Activity {

    private Button buttonSim;
    private Button buttonNao;

    private final String contactFileName = "contact.txt";

    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_medication);

        buttonSim = (Button) findViewById(R.id.buttonSim);
        buttonNao = (Button) findViewById(R.id.buttonNao);



        if(checkPermission(Manifest.permission.SEND_SMS)){
            buttonNao.setEnabled(true);
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
            man.sendTextMessage(number, null, "Não tomou a medicação na hora correcta!", null, null);
            Toast.makeText(getApplicationContext(), "SMS enviada.",Toast.LENGTH_LONG).show();

            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + c.cell));

            finish();
        } else {
            Toast.makeText(getApplicationContext(),"SMS não enviada, por favor tente de novo.", Toast.LENGTH_LONG).show();
        }

    }



}
