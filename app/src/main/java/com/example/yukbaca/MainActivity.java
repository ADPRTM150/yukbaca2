package com.example.yukbaca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yukbaca.angka.Angka;
import com.example.yukbaca.huruf.Huruf;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private Button btnpandu, btnhuruf, btnangka, btnT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnpandu = findViewById(R.id.btnortu);
        btnhuruf = findViewById(R.id.btnhuruf);
        btnangka = findViewById(R.id.btnangka);
        btnT = findViewById(R.id.btn_Test);
        btnT.setOnClickListener(this);
        btnpandu.setOnClickListener(this);
        btnhuruf.setOnClickListener(this);
        btnangka.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnortu:
                Intent intent1 = new Intent(MainActivity.this, PanduanOrtu.class);
                startActivity(intent1);
                break;
            case R.id.btnhuruf:
                Intent intent2 = new Intent(MainActivity.this, Huruf.class);
                startActivity(intent2);
                break;
            case R.id.btnangka:
                Intent int3 = new Intent(MainActivity.this, Angka.class);
                startActivity(int3);
                break;
            case R.id.btn_Test:
                Intent int4 = new Intent(MainActivity.this, Test_Bermain_welcome.class);
                startActivity(int4);
                break;
        }
    }
}