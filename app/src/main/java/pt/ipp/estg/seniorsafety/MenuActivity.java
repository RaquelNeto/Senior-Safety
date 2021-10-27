package pt.ipp.estg.seniorsafety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import pt.ipp.estg.seniorsafety.DetetorQuedas.MainActivity;
import pt.ipp.estg.seniorsafety.medicamentos.PaginaInicial_Medicamentos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;



public class MenuActivity extends AppCompatActivity {

    Button mButtonLogout, mButtonMedicamentos, mButtonMobilidade, mButtonLapsos;
    FirebaseAuth mFirebaseAuth;
    private double latitude, longitude;
    private Location mLastLocation;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private static final int MY_PERMISSION_CODE = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        checkLocationPermission();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        FirebaseAuth.getInstance();

        mButtonLogout = (Button) findViewById(R.id.buttonLogout);
        mButtonMedicamentos = (Button) findViewById(R.id.buttonMedicamentos);
        mButtonMobilidade = (Button) findViewById(R.id.buttonMobilidade);
        mButtonLapsos = (Button) findViewById(R.id.buttonLapsos);

        //para abrir o menu dos Lapsos
        mButtonLapsos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, MenuLapsosMemoria.class);
                startActivity(intent);
            }
        });

        //para abrir o menu dos Medicamentos
        mButtonMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, PaginaInicial_Medicamentos.class);
                startActivity(intent);
            }
        });

        //para abrir o menu dos Lapsos
        mButtonMobilidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, MainActivity.class));
            }
        });

        //para fazer logout da atividade
        mButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut(); //logout
                startActivity(new Intent(MenuActivity.this, LoginActivity.class));
                finish();

            }
        });

    }


    private boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) ) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION
                }, MY_PERMISSION_CODE);
            }
            else {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION
                }, MY_PERMISSION_CODE);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_perfil:
                //Toast.makeText(this, "Perfil selecionado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Perfil.class);
                startActivity(intent);
                return true;
            case R.id.action_mapa:
                Intent i = new Intent(this, pt.ipp.estg.seniorsafety.mapa.MapaActivity.class);
                startActivity(i);
                Toast.makeText(this, "Mapa selecionado", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.buttonLogout:
               finish();
            default:
                return super.onOptionsItemSelected(item);

        }

    }

}

