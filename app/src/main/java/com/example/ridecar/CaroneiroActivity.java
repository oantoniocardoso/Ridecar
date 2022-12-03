package com.example.ridecar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;


import android.Manifest;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.os.Bundle;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class CaroneiroActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    private GoogleMap mMap;
    Marker marker1 = null;
    Marker marker2 = null;
    Fragment fragment = new MenuFragment();
    ImageView callFragment;
    ImageView callFragmentVoltar;
    ImageView callNotiicacao;
    LocationManager locationManager;
    Button button;
    SearchView searchViewPartida;
    SearchView searchViewDestino;

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

        searchViewPartida = findViewById(R.id.idSearchViewPartida);
        searchViewDestino = findViewById(R.id.idSearchViewDestino);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(this,"Atenção, ligue o GPS!", Toast.LENGTH_LONG).show();
        }else
            Toast.makeText(CaroneiroActivity.this,
                    "Aguarde, conectando ao GPS!", Toast.LENGTH_LONG).show();

        button = findViewById(R.id.button3);
        button.setOnClickListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        try{
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    if (marker1 == null) {
                        openLoadingDialog();
                        LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
                        marker1 = mMap.addMarker(new MarkerOptions().position(latlng).title("Você está aqui!"));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 10));
                    }
                }
                public void onStatusChanged(String provider, int status, Bundle extras) { }

                public void onProviderEnabled(String provider) { }

                public void onProviderDisabled(String provider) { }
            };
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

        }catch(SecurityException ex){}

        try{
            searchViewPartida.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    String nomePartida = searchViewPartida.getQuery().toString();
                    List<Address> addressList = null;

                    if (nomePartida != null || nomePartida.equals("")) {
                        Geocoder geocoder = new Geocoder(CaroneiroActivity.this);
                        try {
                            addressList = geocoder.getFromLocationName(nomePartida, 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Address address = addressList.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                        if (marker1 != null) {
                            marker1.remove();
                            marker1 = null;
                        }

                        if (marker1 == null){
                            marker1 = mMap.addMarker(new MarkerOptions().position(latLng).title(nomePartida));
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                        }
                    }
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });

            searchViewDestino.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    String nomeDestino = searchViewDestino.getQuery().toString();
                    List<Address> addressList = null;

                    if (nomeDestino != null || nomeDestino.equals("")) {
                        Geocoder geocoder = new Geocoder(CaroneiroActivity.this);
                        try {
                            addressList = geocoder.getFromLocationName(nomeDestino, 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Address address = addressList.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                        if (marker2 != null) {
                            marker2.remove();
                            marker2 = null;
                        }

                        if (marker2 == null) {
                            marker2 = mMap.addMarker(new MarkerOptions().position(latLng).title(nomeDestino));
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                        }
                    }
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }catch (SecurityException ex){}
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        this.recreate();
    }

    public void openLoadingDialog()
    {
        loadingDialog loadingDialog = new loadingDialog(this);
        loadingDialog.startLoadingDialog();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.dismisDialog();
            }
        },4000);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.call_fragment){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            callFragment.setVisibility(View.GONE);
            callFragmentVoltar.setVisibility(View.VISIBLE);
            button.setVisibility(View.GONE);
            searchViewPartida.setVisibility(View.GONE);
            searchViewDestino.setVisibility(View.GONE);
        }else if(view.getId() == R.id.call_fragmentVoltar){
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            callFragmentVoltar.setVisibility(View.GONE);
            callFragment.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);
            searchViewPartida.setVisibility(View.VISIBLE);
            searchViewDestino.setVisibility(View.VISIBLE);
        }else if(view.getId() == R.id.call_notificacao){
            startActivity(new Intent(this, NotificacaoActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        ImageView call_fragment = (ImageView) findViewById(R.id.call_fragment);
        ImageView call_fragmentVoltar = (ImageView) findViewById(R.id.call_fragmentVoltar);

        if(call_fragment != null || call_fragmentVoltar != null){
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            callFragmentVoltar.setVisibility(View.GONE);
            callFragment.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);
        }else {
            super.onBackPressed();
        }
    }
}