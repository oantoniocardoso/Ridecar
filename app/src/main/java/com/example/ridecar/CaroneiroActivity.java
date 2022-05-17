package com.example.ridecar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CaroneiroActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView callFragment;
    ImageView callFragmentVoltar;
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
}