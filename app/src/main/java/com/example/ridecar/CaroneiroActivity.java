package com.example.ridecar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Scanner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

//public class AppCompatActivityTest extends FragmentActivity implements OnMapReadyCallback {}

public class CaroneiroActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    ImageView callFragment;
    ImageView callFragmentVoltar;
    ImageView callNotiicacao;
    Fragment fragment = new MenuFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_caroneiro);

        callFragment = findViewById(R.id.call_fragment);
        callFragment.setOnClickListener(this);

        callFragmentVoltar = findViewById(R.id.call_fragmentVoltar);
        callFragmentVoltar.setOnClickListener(this);
        callFragmentVoltar.setVisibility(View.GONE);

        callNotiicacao = findViewById(R.id.call_notificacao);
        callNotiicacao.setOnClickListener(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.call_fragment){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            callFragment.setVisibility(View.GONE);
            callFragmentVoltar.setVisibility(View.VISIBLE);
        }else if(view.getId() == R.id.call_fragmentVoltar){
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            callFragmentVoltar.setVisibility(View.GONE);
            callFragment.setVisibility(View.VISIBLE);
        }else if(view.getId() == R.id.call_notificacao){
            startActivity(new Intent(this, NotificacaoActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        ImageView frag = (ImageView) findViewById(R.id.call_fragment);

        if(frag != null){
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            callFragmentVoltar.setVisibility(View.GONE);
            callFragment.setVisibility(View.VISIBLE);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng Uniftec = new LatLng(-29.173624386319837, -51.218501087199);
        googleMap.addMarker(new MarkerOptions().position(Uniftec).title("Uniftec"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Uniftec));
    }
}