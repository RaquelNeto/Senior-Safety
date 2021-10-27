package pt.ipp.estg.seniorsafety;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Perfil extends AppCompatActivity {

    TextView mTextViewNomeMenu, mTextViewEmailMenu, mTextViewNumeroMenu, mTextViewDataMenu, mTextViewSexo;
    Button mButtonMenuPrincipal;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore mFirebaseFirestore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        mTextViewNomeMenu = (TextView) findViewById(R.id.textViewNomeMenu);
        mTextViewEmailMenu = (TextView) findViewById(R.id.textViewEmailMenu);
        mTextViewNumeroMenu = (TextView) findViewById(R.id.textViewNumeroMenu);
        mTextViewDataMenu = (TextView) findViewById(R.id.textViewDataMenu);
        mButtonMenuPrincipal = (Button) findViewById(R.id.buttonMenuPrincipal);


        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseFirestore = FirebaseFirestore.getInstance();

        userId = mFirebaseAuth.getCurrentUser().getUid();

        DocumentReference documentReference = mFirebaseFirestore.collection("utilizadores").document(userId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                //para ir buscar os valores a base de dados
                mTextViewNomeMenu.setText(documentSnapshot.getString("cNome")); //field nome que está na base de dados
                mTextViewEmailMenu.setText(documentSnapshot.getString("email"));
                mTextViewNumeroMenu.setText(documentSnapshot.getString("numero"));
                mTextViewDataMenu.setText(documentSnapshot.getString("dataNascimento"));
            }
        });

        mButtonMenuPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Perfil.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }


}
