package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Configuration.getInstance().load(getApplicationContext(),
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        MapView mapView = findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        //Coordenadas de Santiago
        double santiagoLatitude = -33.4489;
        double santiagoLongitude = -70.6693;

        //Crear objetos GeoPoint para los marcadores
        GeoPoint santiagoPoint = new GeoPoint(santiagoLatitude, santiagoLongitude);

        //Crear marcadores con títulos y descripciones
        Marker santiagoMarker = new Marker(mapView);
        santiagoMarker.setPosition(santiagoPoint);
        santiagoMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        santiagoMarker.setTitle("Santiago, Chile");
        santiagoMarker.setSnippet("Capital de Chile");
        Marker valparaisoMarker = new Marker(mapView);

        //Agregar marcadores al mapa
        mapView.getOverlays().add(santiagoMarker);
        mapView.getOverlays().add(valparaisoMarker);

        // Centrar el mapa en Santiago, Chile
        IMapController mapController = mapView.getController();
        mapController.setCenter(santiagoPoint);
        mapController.setZoom(14);
    }
    public void openGyroscope(View view) {
        startActivity(new Intent(MainActivity.this, GyroscopeActivity.class));
    }
}