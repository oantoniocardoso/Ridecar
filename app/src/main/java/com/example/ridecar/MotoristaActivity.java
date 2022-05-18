package com.example.ridecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MotoristaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_motorista);
    }

    public void botaoVoltarOnClick(View v) { startActivity(new Intent(this, CaroneiroActivity.class));}
}