package com.coordinate.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.PolygonOptions;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MapView mapView;
    private LandView mLandView;
    private MapboxMap mMap;
    private List<LatLng> polygonLatLngList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.mapview);
        mLandView = findViewById(R.id.landView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(mapboxMap -> {
            this.mMap = mapboxMap;
            addPolygon();
        });
        findViewById(R.id.btn_show).setOnClickListener(v ->
                mLandView.setPointList(polygonLatLngList)
        );
    }

    private void addPolygon() {
        polygonLatLngList.add(new LatLng(-37.522585, 144.685699));
        polygonLatLngList.add(new LatLng(-37.534611, 144.708873));
        polygonLatLngList.add(new LatLng(-37.530883, 144.678833));
        polygonLatLngList.add(new LatLng(-37.547115, 144.667503));
        polygonLatLngList.add(new LatLng(-37.530643, 144.660121));
        polygonLatLngList.add(new LatLng(-37.533529, 144.636260));
        polygonLatLngList.add(new LatLng(-37.521743, 144.659091));
        polygonLatLngList.add(new LatLng(-37.510677, 144.648792));
        polygonLatLngList.add(new LatLng(-37.515008, 144.664070));
        polygonLatLngList.add(new LatLng(-37.502496, 144.669048));
        polygonLatLngList.add(new LatLng(-37.515369, 144.678489));
        polygonLatLngList.add(new LatLng(-37.506346, 144.702007));
        polygonLatLngList.add(new LatLng(-37.522585, 144.685699));
        mMap.addPolygon(new PolygonOptions()
                .addAll(polygonLatLngList)
                .fillColor(Color.RED)
        );
        mMap.addPolyline(new PolylineOptions()
                .addAll(polygonLatLngList)
                .width(2f)
                .color(Color.WHITE)
        );
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
