package com.example.ridecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

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

    public void botaoCaroneiroOnClick(View v) { startActivity(new Intent(this, CaroneiroActivity.class));}
}