package com.example.arenda;

import android.app.Activity;
import android.net.Uri;
import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

Uri address = Uri.parse(getString(R.string.url_p));
Intent openlink = new Intent(Intent.ACTION_VIEW, address);
startActivity(openlink);

    }

    // ���������� ����� ������� �� "������������" ���������.
    @Override
    public void onDestroy(){
        // �������� ��� �������. ��� �������� ���������� ������
        // �������, �������� ���������� � ����� ������ � �. �.
        super.onDestroy();
    }

//**

}