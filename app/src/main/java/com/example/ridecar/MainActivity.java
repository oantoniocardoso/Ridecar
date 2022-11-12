package com.example.ridecar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        TextView textViewEsqueceuSenha = (TextView) findViewById(R.id.textViewEsqueceuSenha);
        TextView textViewCadastro = (TextView) findViewById(R.id.textViewCadastro);

        SpannableString esqueceuSenha = new SpannableString("Esqueceu a sua senha?");
        SpannableString cadastro = new SpannableString("Não possui uma conta? Cadastrar-se.");

        esqueceuSenha.setSpan(new CustomClickableSpanEsqueceuSenha(), 0, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        cadastro.setSpan(new CustomClickableSpanCadastro(), 22, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textViewEsqueceuSenha.setText(esqueceuSenha);
        textViewCadastro.setText(cadastro);

        textViewEsqueceuSenha.setMovementMethod(LinkMovementMethod.getInstance());
        textViewCadastro.setMovementMethod(LinkMovementMethod.getInstance());

        Button btnLogin = findViewById(R.id.buttonEntrar);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticateUser();
            }
        });
    }

    public void authenticateUser(){
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etSenha = findViewById(R.id.etSenha);

        String email = etEmail.getText().toString();
        String senha = etSenha.getText().toString();

        if(email.isEmpty() || senha.isEmpty()){
            Toast.makeText(MainActivity.this,
                    "Por favor preencha todos os campos.", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            showCaroneiroActivity();
                        } else {
                            Toast.makeText(MainActivity.this,
                                    "Usuário não autenticado!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    class CustomClickableSpanEsqueceuSenha extends ClickableSpan {

        @Override
        public void onClick(View textView) {
            startActivity(new Intent(MainActivity.this, RecuperarSenhaActivity.class));
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(getColor(R.color.colorSecondary));
            ds.setUnderlineText(false);
        }
    }

    class CustomClickableSpanCadastro extends ClickableSpan {

        @Override
        public void onClick(View textView) {
            startActivity(new Intent(MainActivity.this, CadastroActivity.class));
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(getColor(R.color.colorSecondary));
            ds.setUnderlineText(false);
        }
    }

    private void showCaroneiroActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}