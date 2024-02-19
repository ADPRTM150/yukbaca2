package com.example.yukbaca.angka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yukbaca.Instruksi;
import com.example.yukbaca.PanduanOrtu;
import com.example.yukbaca.R;
import com.example.yukbaca.huruf.Huruf;
import com.example.yukbaca.huruf.HurufKecil;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class Angka extends AppCompatActivity implements View.OnClickListener{
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private Button btnpandu, btnins,btnang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angka);
        btnpandu = findViewById(R.id.btnortu);
        btnins = findViewById(R.id.btninst);
        btnang = findViewById(R.id.btnangka);
        tabLayout = findViewById(R.id.tablayputid);
        btnpandu.setOnClickListener(this);
        btnang.setOnClickListener(this);
        btnins.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnortu:
                Intent intent1 = new Intent(Angka.this, PanduanOrtu.class);
                startActivity(intent1);
                break;
            case R.id.btninst:
                Intent intent2 = new Intent(Angka.this, Instruksi.class);
                startActivity(intent2);
                break;
            case R.id.btnangka:
                Intent intent3 = new Intent(Angka.this, AngkaFix.class);
                startActivity(intent3);
                break;
        }
    }
}