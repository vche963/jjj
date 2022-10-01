package com.example.arendan;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
 
public class MainActivity extends Activity implements OnClickListener {
 
  Button btnWeb,btnExit;
 
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
 
    btnWeb = (Button) findViewById(R.id.btnWeb);

    btnExit = (Button) findViewById(R.id.btnExit);
 
    btnWeb.setOnClickListener(this);
    btnExit.setOnClickListener(this);

   }
 
  @Override
  public void onClick(View v) {
    Intent intent;
    switch (v.getId()) {
    case R.id.btnWeb:
       Uri address = Uri.parse(getString(R.string.url_p));
       Intent openlink = new Intent(Intent.ACTION_VIEW, address);
       startActivity(openlink);
       finish();
      break;
    case R.id.btnExit:
      finishAffinity();
System.exit(0);

      break;

    }
  }
}