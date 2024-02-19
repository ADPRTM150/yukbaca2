package com.example.yukbaca.angka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.yukbaca.Instruksi;
import com.example.yukbaca.R;

import java.util.ArrayList;
import java.util.List;

public class AngkaFix extends AppCompatActivity {
    TextView tvangka;
    List<Angka> angkas;
    ToggleButton sw;
    Button btnintruksi;
    public static final Integer RecordAudioRequestCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angka_fix);


        btnintruksi = findViewById(R.id.btn_intruksi);
        btnintruksi.setOnClickListener(v->{
            Intent intruksi = new Intent(this, Instruksi.class);
            startActivity(intruksi);
        });

        tvangka = findViewById(R.id.tv_angka);

        sw = findViewById(R.id.togglebutton);
        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sw.isChecked()){
                    tvangka.setAllCaps(true);
                    panggilrv();
                } else {
                    tvangka.setAllCaps(false);
                    panggilrv();
                }
            }
        });
        panggilarray();
        panggilrv();
    }

    private void panggilrv() {
        RecyclerView myrv = findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, angkas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        myrv.setLayoutManager(layoutManager);
        myrv.setAdapter(myAdapter);
    }


    private void panggilarray() {
        angkas = new ArrayList<>();
        angkas.add(new Angka("1", R.raw.satu));
        angkas.add(new Angka("2", R.raw.dua));
        angkas.add(new Angka("3", R.raw.tiga));
        angkas.add(new Angka("4", R.raw.empat));
        angkas.add(new Angka("5", R.raw.lima));
        angkas.add(new Angka("6", R.raw.enam));
        angkas.add(new Angka("7", R.raw.tujuh));
        angkas.add(new Angka("8", R.raw.delapan));
        angkas.add(new Angka("9", R.raw.sembilan));
        angkas.add(new Angka("10", R.raw.sepuluh));
        angkas.add(new Angka("11", R.raw.sebelas));
        angkas.add(new Angka("12", R.raw.duabelas));
        angkas.add(new Angka("13", R.raw.tigabelas));
        angkas.add(new Angka("14", R.raw.empatbelas));
        angkas.add(new Angka("15", R.raw.limabelas));
        angkas.add(new Angka("16", R.raw.enambelas));
        angkas.add(new Angka("17", R.raw.tujuhbelas));
        angkas.add(new Angka("18", R.raw.delapanbelas));
        angkas.add(new Angka("19", R.raw.sembilanbelas));
        angkas.add(new Angka("20", R.raw.duapuluh));
        angkas.add(new Angka("21", R.raw.duasatu));
        angkas.add(new Angka("22", R.raw.duadua));
        angkas.add(new Angka("23", R.raw.duatiga));
        angkas.add(new Angka("24", R.raw.duaempat));
        angkas.add(new Angka("25", R.raw.dualima));
    }


    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

        private Context mContext ;
        private List<Angka> mData ;


        public RecyclerViewAdapter(Context mContext, List<Angka> mData) {
            this.mContext = mContext;
            this.mData = mData;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view ;
            LayoutInflater mInflater = LayoutInflater.from(mContext);
            view = mInflater.inflate(R.layout.cardview_angka,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, final int position) {
            if (sw.isChecked()){
                holder.tv_Angka_title.setText(mData.get(position).getTitle());
            } else {
                holder.tv_Angka_title.setText(mData.get(position).getTitle());
            }

            MediaPlayer suara = MediaPlayer.create(mContext, (mData.get(position).getSound()));


            holder.cardView.setOnClickListener(v -> {

                tvangka.setText(mData.get(position).getTitle());
                suara.start();


            });



        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv_Angka_title;

            CardView cardView ;

            TextView tvAngka;

            public MyViewHolder(View itemView) {
                super(itemView);

                tv_Angka_title = itemView.findViewById(R.id.angka_title_id) ;
                cardView = itemView.findViewById(R.id.cardview_angka_id);
                tvAngka = itemView.findViewById(R.id.tv_angka);


            }
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }



    private class Angka {
        private String Title;
        private int sound;


        public Angka(String title, int sound) {
            Title = title;

            this.sound = sound;
        }

        public int getSound() {
            return sound;
        }

        public void setSound(int sound) {
            this.sound = sound;
        }

        public String getTitle() {
            return Title;
        }



        public void setTitle(String title) {
            Title = title;
        }


    }
}