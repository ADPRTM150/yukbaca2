package com.example.yukbaca.huruf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yukbaca.Instruksi;
import com.example.yukbaca.PanduanOrtu;
import com.example.yukbaca.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class Huruf extends AppCompatActivity implements View.OnClickListener {
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private Button btnpandu, btnins,btnhur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huruf);
        btnpandu = findViewById(R.id.btnortu);
        btnins = findViewById(R.id.btninst);
        btnhur = findViewById(R.id.btnhuruf);
        tabLayout = findViewById(R.id.tablayputid);
        btnpandu.setOnClickListener(this);
        btnhur.setOnClickListener(this);
        btnins.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnortu:
                Intent intent1 = new Intent(Huruf.this, PanduanOrtu.class);
                startActivity(intent1);
                break;
            case R.id.btninst:
                Intent intent2 = new Intent(Huruf.this, Instruksi.class);
                startActivity(intent2);
                break;
            case R.id.btnhuruf:
                Intent intent3 = new Intent(Huruf.this, HurufKecil.class);
                startActivity(intent3);
                break;
        }
    }
}