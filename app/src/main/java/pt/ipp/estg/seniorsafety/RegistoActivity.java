package pt.ipp.estg.seniorsafety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistoActivity extends AppCompatActivity  {

    EditText mNome, mEmail, mPassword, mNumero, mData;
    RadioButton mRadioMasculino, mRadioFeminino;
    RadioGroup mRadioGroup;
    Button mButtonRegistar;
    FirebaseAuth mFirebaseAuth;
    ProgressBar progressBar;
    FirebaseFirestore mFirebaseFirestore;
    String userID;

    private static final String TAG = "RegistoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo);

        mNome = (EditText) findViewById(R.id.editTextNome);
        mEmail = (EditText) findViewById(R.id.editTextEmail);
        mPassword = (EditText) findViewById(R.id.editTextPassword);
        mNumero = (EditText) findViewById(R.id.editTextNumero);
        mData = (EditText) findViewById(R.id.editTextData);
        mButtonRegistar = (Button) findViewById(R.id.buttonRegistar);
        mRadioMasculino = (RadioButton) findViewById(R.id.radioMasculino);
        mRadioFeminino = (RadioButton) findViewById(R.id.radioFeminino);


        mFirebaseAuth = FirebaseAuth.getInstance(); //inicializar Firebase autenticação
        mFirebaseFirestore = FirebaseFirestore.getInstance(); //inicializar a base de dados

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        if(mFirebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MenuActivity.class));
            finish();
        }


        mButtonRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                final String numero = mNumero.getText().toString().trim();
                final String nomeCompleto = mNome.getText().toString().trim();
                final String dataNascimento = mData.getText().toString().trim();
                String feminino = mRadioFeminino.getText().toString().trim();

                if(TextUtils.isEmpty(nomeCompleto)){
                    mNome.setError("Nome é obrigatório");
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email é obrigatório");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password é obrigatória");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("A password tem de ter mais de 6 caracteres");
                    return;
                }

                if(TextUtils.isEmpty(numero)){
                    mNumero.setError("Numero é obrigatório");
                    return;
                }

                if(numero.length() == 10){
                    mNumero.setError("A password tem de ter exatamento 9 numeros");
                    return;
                }

                if(TextUtils.isEmpty(dataNascimento)){
                    mData.setError("Data de Nascimento é obrigatório");
                    return;
                }

                if(TextUtils.isEmpty(feminino)){
                    mData.setError("Gent é obrigatório");
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);

                //registar o utilizador na Firebase
                mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegistoActivity.this, "Utilizador criado", Toast.LENGTH_SHORT).show();
                            userID = mFirebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = mFirebaseFirestore.collection("utilizadores").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("cNome",nomeCompleto);
                            user.put("email", email);
                            user.put("numero", numero);
                            user.put("dataNascimento", dataNascimento);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG,"Sucesso: perfil do utilizador foi criado para " + userID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),MenuActivity.class));

                        }else{
                            // se ja existir dá erro
                            Toast.makeText(RegistoActivity.this, "Erro!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });



    }
}

