package com.example.ridecar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroActivity extends AppCompatActivity {

   private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastro);

        mAuth = FirebaseAuth.getInstance();

        Button btnRegister = findViewById(R.id.buttonCadastrar);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser(){
        EditText etNome = findViewById(R.id.nome);
        EditText etCpf = findViewById(R.id.cpf);
        EditText etEmail = findViewById(R.id.email);
        EditText etSenha = findViewById(R.id.senha);
        EditText etCelular = findViewById(R.id.celular);

        String nome = etNome.getText().toString();
        String cpf = etCpf.getText().toString();
        String email = etEmail.getText().toString();
        String senha = etSenha.getText().toString();
        String celular = etCelular.getText().toString();

        if(nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || senha.isEmpty() || celular.isEmpty()){
            Toast.makeText(CadastroActivity.this,
                    "Por favor preencha todos os campos.", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(nome, cpf, email, celular);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    showMainActivity();
                                }
                            });
                        }else if(senha.length() < 6){
                            Toast.makeText(CadastroActivity.this,
                                    "A senha deve conter no mínimo 6 números!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(CadastroActivity.this,
                                    "Falha na autenticação.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void showMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void botaoVoltarOnClick(View v) { startActivity(new Intent(this, MainActivity.class));}
}