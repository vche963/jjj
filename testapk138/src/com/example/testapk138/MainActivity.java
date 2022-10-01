package com.example.testapk138;

import java.util.Date;
import java.util.Locale; 
import android.app.Activity;
import android.net.Uri;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

 
public class MainActivity extends Activity {
 
  TextView tvEnabledGPS;
  TextView tvStatusGPS;
  TextView tvLocationGPS;
  TextView tvEnabledNet;
  TextView tvStatusNet;
  TextView tvLocationNet;
  TextView tvSmsSoob;
 
  private LocationManager locationManager;
  StringBuilder sbGPS = new StringBuilder();
  StringBuilder sbNet = new StringBuilder();
 
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    tvEnabledGPS = (TextView) findViewById(R.id.tvEnabledGPS);
    tvStatusGPS = (TextView) findViewById(R.id.tvStatusGPS);
    tvLocationGPS = (TextView) findViewById(R.id.tvLocationGPS);
    tvEnabledNet = (TextView) findViewById(R.id.tvEnabledNet);
    tvStatusNet = (TextView) findViewById(R.id.tvStatusNet);
    tvLocationNet = (TextView) findViewById(R.id.tvLocationNet);
    tvSmsSoob = (TextView) findViewById(R.id.tvSmsSoob);
 
    locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
  }
 
  @Override
  protected void onResume() {
    super.onResume();
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
        1000 * 10, 10, locationListener);
    locationManager.requestLocationUpdates(
        LocationManager.NETWORK_PROVIDER, 1000 * 10, 10,
        locationListener);
    checkEnabled();
  }
 
  @Override
  protected void onPause() {
    super.onPause();
    locationManager.removeUpdates(locationListener);
  }
 
  private LocationListener locationListener = new LocationListener() {
 
    @Override
    public void onLocationChanged(Location location) {
      showLocation(location);
    }
 
    @Override
    public void onProviderDisabled(String provider) {
      checkEnabled();
    }
 
    @Override
    public void onProviderEnabled(String provider) {
      checkEnabled();
      showLocation(locationManager.getLastKnownLocation(provider));
    }
 
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
      if (provider.equals(LocationManager.GPS_PROVIDER)) {
        tvStatusGPS.setText("Status: " + String.valueOf(status));
      } else if (provider.equals(LocationManager.NETWORK_PROVIDER)) {
        tvStatusNet.setText("Status: " + String.valueOf(status));
      }
    }
  };
 
  private void showLocation(Location location) {
    if (location == null)
      return;
    if (location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
      tvLocationGPS.setText(formatLocation(location));
      tvSmsSoob.setText(format2Location(location));
    } else if (location.getProvider().equals(
        LocationManager.NETWORK_PROVIDER)) {
      tvLocationNet.setText(formatLocation(location));
      tvSmsSoob.setText(format2Location(location));
    }
  }
 
  private String formatLocation(Location location) {
    if (location == null)
      return "";
    return String.format(
        "Coordinates: lat = %1$.4f, lon = %2$.4f, time = %3$tF %3$tT",
        location.getLatitude(), location.getLongitude(), new Date(
            location.getTime()));
  }
  private String format2Location(Location location) {
    if (location == null)
      return "";
    return String.format(Locale.ENGLISH,
    "На карте http://m.702702.ru:81/nakarte.aspx?koord= %1$.5f,%2$.5f",
         location.getLongitude(),location.getLatitude());
  }
 
  private void checkEnabled() {
    tvEnabledGPS.setText("Enabled: "
        + locationManager
            .isProviderEnabled(LocationManager.GPS_PROVIDER));
    tvEnabledNet.setText("Enabled: "
        + locationManager
            .isProviderEnabled(LocationManager.NETWORK_PROVIDER));
  }
 
  public void onClickLocationSettings(View view) {
    startActivity(new Intent(
        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
  };
  public void onClickSendSMS(View view) {
     Intent smsIntent = new Intent(Intent.ACTION_VIEW);
     smsIntent.setData(Uri.parse("smsto:"));     
     smsIntent.setType("vnd.android-dir/mms-sms");
     //smsIntent.putExtra("address"  , new String ("01234"));
    // smsIntent.putExtra("sms_body", tvLocationGPS.getText().toString()); 
     smsIntent.putExtra("sms_body", tvSmsSoob.getText().toString()); 
     startActivity(smsIntent);
      try {
         startActivity(smsIntent);
         finish();        
      } catch (android.content.ActivityNotFoundException ex) {
         Toast.makeText(MainActivity.this, 
         "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
      }


  };
 
}