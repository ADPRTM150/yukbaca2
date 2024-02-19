package com.example.yukbaca;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Test_Bermain_welcome extends AppCompatActivity {
Button btnNama;
TextView tvData, tvInput;
    private static final String TAG = "DatabaseHelper";
    //Variable Untuk Inisialisasi Database DBMahasiswa

    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test__bermain_welcome);
        //Inisialisasi dan Mendapatkan Konteks dari DBMahasiswa

        //Inisialisasi dan Mendapatkan Konteks dari DBMahasiswa
        databaseHelper = new DatabaseHelper(getBaseContext());
        final String CREDENTIAL_SHARED_PREF = "our_shared_pref";
        btnNama = findViewById(R.id.btn_mulai_test);
        btnNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Test_Bermain_welcome.this);
                //Memasang Title / Judul pada Custom Dialog
                dialog.setTitle("Contoh Custom Dialog");
                //Memasang Desain Layout untuk Custom Dialog
                dialog.setContentView(R.layout.dialog_nama);

                //Memasang Listener / Aksi saat tombol OK di Klik
                Button DialogButton = dialog.findViewById(R.id.btnNama);
                EditText namapref = dialog.findViewById(R.id.nama);
                DialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String strUsername = namapref.getText().toString();
                        if(namapref.getText().toString().isEmpty()) {
                            // editText is empty
                            namapref.setError("Masukkan Nama Anak");
                        } else {
                            // editText is not empty
                            SharedPreferences credentials = getSharedPreferences(CREDENTIAL_SHARED_PREF, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = credentials.edit();
                            editor.putString("Username", strUsername);
                            editor.commit();
                            toastMessage("Nama :"+strUsername);
                            dialog.dismiss();
                            Intent intent2 = new Intent(Test_Bermain_welcome.this, Test_Bermain.class);
                            startActivity(intent2);
                            finish();
                        }
                        


                    }
                });

                dialog.show();
            }

        });

        tvData = findViewById(R.id.lihatdata);
        tvData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Test_Bermain_welcome.this, Test_Bermain_Nilai.class);
                startActivity(intent2);
            }
        });

        tvInput = findViewById(R.id.input);
        tvInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                SQLiteDatabase create = databaseHelper.getWritableDatabase();
                //Membuat Map Baru, Yang Berisi Nama Kolom dan Data Yang Ingin Dimasukan
                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.MyColumns.NAME, "Adi");
                values.put(DatabaseHelper.MyColumns.NILAI, "80");
                values.put(DatabaseHelper.MyColumns.NILAI1, "80");
                values.put(DatabaseHelper.MyColumns.NILAI2, "70");
                values.put(DatabaseHelper.MyColumns.NILAI3, "60");
                create.insert(DatabaseHelper.MyColumns.TABLE_NAME, null, values);


            }
        });
        tvInput.setVisibility(View.GONE);
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}