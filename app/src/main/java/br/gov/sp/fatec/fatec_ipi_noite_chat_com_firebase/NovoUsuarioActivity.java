package br.gov.sp.fatec.fatec_ipi_noite_chat_com_firebase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import br.gov.sp.fatec.fatec_ipi_noite_chat_com_firebase.util.ElementEnabler;

public class NovoUsuarioActivity extends AppCompatActivity {

    private EditText loginNovoUsuarioEditText;
    private EditText senhaNovoUsuarioEditText;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);

        loginNovoUsuarioEditText = findViewById(R.id.loginNovoUsuarioEditText);
        senhaNovoUsuarioEditText = findViewById(R.id.senhaNovoUsuarioEditText);
        Button signupButton = findViewById(R.id.signupButton);
        ElementEnabler.textViews(signupButton, loginNovoUsuarioEditText, senhaNovoUsuarioEditText);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void criarNovoUsuario (View view) {
        String login = loginNovoUsuarioEditText.getText().toString();
        String senha = senhaNovoUsuarioEditText.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(login, senha).addOnSuccessListener(result -> {
            Toast.makeText(this, getString(R.string.cadastro_sucesso), Toast.LENGTH_SHORT).show();
            finish();
        }).addOnFailureListener(erro -> Toast.makeText(this, getString(R.string.cadastro_falha), Toast.LENGTH_SHORT).show());
    }
}
