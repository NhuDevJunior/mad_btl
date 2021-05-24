package com.example.android.newsfeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.example.android.newsfeed.databinding.ActivityWeatherBinding;
import com.example.android.newsfeed.viewmodels.WeatherViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationProviderClient;
    private WeatherViewModel weatherViewModel;
    private ActivityWeatherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWeatherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Display the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set up view model
        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

        // Get the current location
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        fusedLocationProviderClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null);
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location != null) {
                        // Get information of the location using Geocoder
                        Geocoder geocoder = new Geocoder(this);
                        try {
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
                            if (addresses != null && !addresses.isEmpty()) {
                                for (Address address : addresses) {
                                    if (address != null) {
                                        // Get the weather of current location
                                        weatherViewModel.getCurrentLocationWeather(address.getLocality(), address.getLatitude(), address.getLongitude());
                                        binding.weatherLocation.setText(address.getLocality());
                                        break;
                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

        // Observe the weather change
        weatherViewModel.getWeather().observe(this, weather -> {
            if (weather != null) {
                String weatherDescription = weather.getWeatherDescription().get(0).getDescription();
                binding.weatherDescription.setText(weatherDescription.substring(0, 1).toUpperCase() + weatherDescription.substring(1));
                Glide.with(this).load(weather.getWeatherDescription().get(0).getIconUrl()).into(binding.weatherIcon);
                binding.weatherTemp.setText(weather.getWeatherInfo().getTempMinString() + " - " + weather.getWeatherInfo().getTempMaxString());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}