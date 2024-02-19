package com.example.yukbaca;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class DetailNilai extends AppCompatActivity implements View.OnClickListener {
    private TextView tvResult, tvnama;
    private Button btnHome;
    MediaPlayer yea;
    String strUsername, nama;
    String score, scorehuruf, scorebunyi, scorekata;
    DatabaseHelper databaseHelper;
    private final String CREDENTIAL_SHARED_PREF = "our_shared_pref";
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            Objects.requireNonNull(this.getSupportActionBar()).hide();
        }
        catch (NullPointerException ignored){}
        setContentView(R.layout.activity_detail_nilai);
        SharedPreferences credentials = getSharedPreferences(CREDENTIAL_SHARED_PREF, Context.MODE_PRIVATE);
        strUsername = credentials.getString("Username", null);

        tvResult = findViewById(R.id.txt_result);
        tvnama = findViewById(R.id.selamat);
        TextView tvHuruf = findViewById(R.id.txt_result_huruf);
        TextView tvBunyi = findViewById(R.id.txt_result_bunyi);
        TextView tvKata = findViewById(R.id.txt_result_kata);
        btnHome = findViewById(R.id.btn_home);



        btnHome.setOnClickListener(this);

        Intent intent = getIntent();
        score = intent.getStringExtra("score");

        scorehuruf = intent.getStringExtra("scorehuruf");
        tvHuruf.setText("" + scorehuruf);

        scorebunyi = intent.getStringExtra("scorebunyi");
        tvHuruf.setText("" + scorebunyi);

        scorekata = intent.getStringExtra("scorekata");
        tvHuruf.setText("" + scorekata);

        nama = intent.getStringExtra("nama");

        tvResult.setText("" + score);
        tvHuruf.setText("" + scorehuruf);
        tvBunyi.setText("" + scorebunyi);
        tvKata.setText("" + scorekata);
        tvnama.setText(nama);





    }

    @Override
    public void onClick(View v) {
//        SQLiteDatabase create = databaseHelper.getWritableDatabase();
//        //Membuat Map Baru, Yang Berisi Nama Kolom dan Data Yang Ingin Dimasukan
//        ContentValues values = new ContentValues();
//        values.put("Nama", strUsername);
//        values.put("Nilai", score);
//        values.put("Nilai1", scorebunyi);
//        values.put("Nilai2", scorehuruf);
//        values.put("Nilai3", scorekata);
//        //Menambahkan Baris Baru, Berupa Data Yang Sudah Diinputkan pada Kolom didalam Database
//        create.insert("Nilai", null, values);

        finish();
    }
}