package com.example.yukbaca.huruf;

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

public class HurufKecil extends AppCompatActivity {
    TextView tvhuruf;
    ToggleButton sw;
    List<Huruf> hurufs;
    Button btnintruksi;
    public static final Integer RecordAudioRequestCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huruf_kecil);


        btnintruksi = findViewById(R.id.btn_intruksi);
        btnintruksi.setOnClickListener(v->{
            Intent intruksi = new Intent(this, Instruksi.class);
            startActivity(intruksi);
        });

        tvhuruf = findViewById(R.id.tv_huruf);

        sw = findViewById(R.id.togglebutton);
        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sw.isChecked()){
                    tvhuruf.setAllCaps(true);
                    panggilrv();
                } else {
                    tvhuruf.setAllCaps(false);
                    panggilrv();
                }
            }
        });



        panggilarray();
        panggilrv();
    }

    private void panggilrv() {
        RecyclerView myrv = findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, hurufs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        myrv.setLayoutManager(layoutManager);
        myrv.setAdapter(myAdapter);
    }


    private void panggilarray() {
        hurufs = new ArrayList<>();
        hurufs.add(new Huruf("a", R.raw.a));
        hurufs.add(new Huruf("b", R.raw.b));
        hurufs.add(new Huruf("c", R.raw.c));
        hurufs.add(new Huruf("d", R.raw.d));
        hurufs.add(new Huruf("e", R.raw.e));
        hurufs.add(new Huruf("f", R.raw.f));
        hurufs.add(new Huruf("g", R.raw.g));
        hurufs.add(new Huruf("h", R.raw.h));
        hurufs.add(new Huruf("i", R.raw.i));
        hurufs.add(new Huruf("j", R.raw.j));
        hurufs.add(new Huruf("k", R.raw.k));
        hurufs.add(new Huruf("l", R.raw.l));
        hurufs.add(new Huruf("m", R.raw.m));
        hurufs.add(new Huruf("n", R.raw.n));
        hurufs.add(new Huruf("o", R.raw.o));
        hurufs.add(new Huruf("p", R.raw.p));
        hurufs.add(new Huruf("q", R.raw.q));
        hurufs.add(new Huruf("r", R.raw.r));
        hurufs.add(new Huruf("s", R.raw.s));
        hurufs.add(new Huruf("t", R.raw.t));
        hurufs.add(new Huruf("u", R.raw.u));
        hurufs.add(new Huruf("v", R.raw.v));
        hurufs.add(new Huruf("w", R.raw.w));
        hurufs.add(new Huruf("x", R.raw.x));
        hurufs.add(new Huruf("y", R.raw.y));
        hurufs.add(new Huruf("z", R.raw.z));
    }


    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

        private Context mContext ;
        private List<Huruf> mData ;


        public RecyclerViewAdapter(Context mContext, List<Huruf> mData) {
            this.mContext = mContext;
            this.mData = mData;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view ;
            LayoutInflater mInflater = LayoutInflater.from(mContext);
            view = mInflater.inflate(R.layout.cardveiw_huruf,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {

            if (sw.isChecked()){
                holder.tv_huruf_title.setText(mData.get(position).getTitle().toUpperCase());

            } else {
                holder.tv_huruf_title.setText(mData.get(position).getTitle().toLowerCase());

            }



            MediaPlayer suara = MediaPlayer.create(mContext, (mData.get(position).getSound()));


            holder.cardView.setOnClickListener(v -> {

                tvhuruf.setText(mData.get(position).getTitle());
                suara.start();


            });



        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv_huruf_title;

            CardView cardView ;

            TextView tvhuruf;

            public MyViewHolder(View itemView) {
                super(itemView);

                tv_huruf_title = itemView.findViewById(R.id.huruf_title_id) ;
                cardView = itemView.findViewById(R.id.cardview_huruf_id);
                tvhuruf = itemView.findViewById(R.id.tv_huruf);


            }
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }



    private class Huruf {
        private String Title;
        private int sound;


        public Huruf(String title, int sound) {
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