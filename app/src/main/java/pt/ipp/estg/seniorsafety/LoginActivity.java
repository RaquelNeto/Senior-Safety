package pt.ipp.estg.seniorsafety;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity{

    EditText mEditTextEmailLogin, mEditTextPasswordLogin;
    Button mButtonLogin;
    TextView mTextViewLogin;
    TextView mTextViewRegisto;
    ProgressBar mProgressBarLogin;
    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEditTextEmailLogin = (EditText) findViewById(R.id.editTextEmailLogin);
        mEditTextPasswordLogin = (EditText) findViewById(R.id.editTextPasswordLogin);
        mTextViewLogin = (TextView) findViewById(R.id.textViewLogin);
        mTextViewRegisto = (TextView) findViewById(R.id.textViewRegistar);
        mButtonLogin = (Button) findViewById(R.id.buttonLogin);
        mProgressBarLogin = (ProgressBar) findViewById(R.id.progressBarLogin);

        mFirebaseAuth = FirebaseAuth.getInstance(); //inicializar Firebase Auth

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mEditTextEmailLogin.getText().toString().trim();
                String password = mEditTextPasswordLogin.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    mEditTextEmailLogin.setError("Email é obrigatório");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mEditTextPasswordLogin.setError("Password é obrigatória");
                    return;
                }

                if(password.length() < 6){
                    mEditTextPasswordLogin.setError("A password tem de ter mais de 6 caracteres");
                    return;
                }


                mProgressBarLogin.setVisibility(View.VISIBLE);

                //autenticação do utilizador
                mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            // Toast exibe mensagens no ecrã
                            Toast.makeText(LoginActivity.this, "O Login foi efetuado com sucesso", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MenuActivity.class));

                        }else{
                            Toast.makeText(LoginActivity.this, "Erro!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        // para quando se clica no Login aqui passar para a pagina do Login
        mTextViewRegisto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegistoActivity.class));
            }
        });

        // para quando se clica no Login aqui passar para a pagina do Login
        mTextViewRegisto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistoActivity.class);
                startActivity(intent);
            }
        });


    }

}

