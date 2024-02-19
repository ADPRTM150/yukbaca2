package com.example.yukbaca.kata;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yukbaca.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Kata_Bermain_Membaca extends AppCompatActivity {
    TextView tvbuku;
    List<Kata> bacasatusukukata;
    List<Kata> bacaduasukukata;
    List<Kata> bacatigasukukata;
    public static final Integer RecordAudioRequestCode = 1;
    private SpeechRecognizer speechRecognizer;
    private TextView editText;
    private ImageView micButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kata__bermain__membaca);
        MediaPlayer salah = MediaPlayer.create(this, R.raw.salah);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }
        tvbuku = findViewById(R.id.namabuku);
        editText = findViewById(R.id.hasiltext);
        micButton = findViewById(R.id.button);
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {
                editText.setText("");
                editText.setHint("Mendengarkan...");
            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {
                editText.setText("");
                editText.setHint("Ulangi...");
                micButton.setImageResource(R.drawable.ic_mic_black_off);
            }

            @Override
            public void onResults(Bundle bundle) {
                micButton.setImageResource(R.drawable.ic_mic_black_off);
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                String lower = data.get(0) ;
                lower.toLowerCase();
                editText.setText(lower);
                String nabu = (String) tvbuku.getText();

                if (lower.equalsIgnoreCase(nabu)){
                    Toast.makeText(Kata_Bermain_Membaca.this, "Benar",Toast.LENGTH_LONG).show();
                    tvbuku.setTextColor(Color.rgb(33,200,66));
                }else {
                    Toast.makeText(Kata_Bermain_Membaca.this, "Salah",Toast.LENGTH_LONG).show();
                    tvbuku.setTextColor(Color.rgb(230,0,0));
                    salah.start();
                }
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        micButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    speechRecognizer.stopListening();
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    micButton.setImageResource(R.drawable.ic_mic_black_24dp);
                    speechRecognizer.startListening(speechRecognizerIntent);
                }
                return false;
            }
        });



        bacasatusukukata = new ArrayList<>();
        bacasatusukukata.add(new Kata("bu"));
        bacasatusukukata.add(new Kata("di"));
        bacasatusukukata.add(new Kata("ma"));
        bacasatusukukata.add(new Kata("no"));
        bacasatusukukata.add(new Kata("ha"));
        bacasatusukukata.add(new Kata("ju"));
        bacasatusukukata.add(new Kata("hi"));
        bacasatusukukata.add(new Kata("ku"));
        bacasatusukukata.add(new Kata("sa"));
        bacasatusukukata.add(new Kata("ma"));

        bacaduasukukata = new ArrayList<>();
        bacaduasukukata.add(new Kata("budi"));
        bacaduasukukata.add(new Kata("buku"));
        bacaduasukukata.add(new Kata("dadu"));
        bacaduasukukata.add(new Kata("babu"));
        bacaduasukukata.add(new Kata("duku"));
        bacaduasukukata.add(new Kata("baca"));
        bacaduasukukata.add(new Kata("coba"));
        bacaduasukukata.add(new Kata("bisa"));
        bacaduasukukata.add(new Kata("sama"));
        bacaduasukukata.add(new Kata("daku"));
        bacaduasukukata.add(new Kata("meja"));
        bacaduasukukata.add(new Kata("baju"));
        bacaduasukukata.add(new Kata("dana"));
        bacaduasukukata.add(new Kata("gajah"));
        bacaduasukukata.add(new Kata("kapal"));

        bacatigasukukata = new ArrayList<>();
        bacatigasukukata.add(new Kata("sepatu"));
        bacatigasukukata.add(new Kata("kepala"));
        bacatigasukukata.add(new Kata("kelapa"));
        bacatigasukukata.add(new Kata("harimau"));
        bacatigasukukata.add(new Kata("kelinci"));
        bacatigasukukata.add(new Kata("pusaka"));
        bacatigasukukata.add(new Kata("domino"));
        bacatigasukukata.add(new Kata("celana"));
        bacatigasukukata.add(new Kata("jerapah"));
        bacatigasukukata.add(new Kata("jerami"));

        //satu suku kata
        RecyclerView myrv = findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, bacasatusukukata);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        myrv.setLayoutManager(layoutManager);
        myrv.setAdapter(myAdapter);

        //dua suku kata
        RecyclerView myrv2 = findViewById(R.id.recyclerview_id2);
        RecyclerViewAdapter myAdapter2 = new RecyclerViewAdapter(this, bacaduasukukata);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        myrv2.setLayoutManager(layoutManager2);
        myrv2.setAdapter(myAdapter2);

        //satu suku kata
        RecyclerView myrv3 = findViewById(R.id.recyclerview_id3);
        RecyclerViewAdapter myAdapter3 = new RecyclerViewAdapter(this, bacatigasukukata);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        myrv3.setLayoutManager(layoutManager3);
        myrv3.setAdapter(myAdapter3);


    }


    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

        private Context mContext ;
        private List<Kata> mData ;


        public RecyclerViewAdapter(Context mContext, List<Kata> mData) {
            this.mContext = mContext;
            this.mData = mData;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view ;
            LayoutInflater mInflater = LayoutInflater.from(mContext);
            view = mInflater.inflate(R.layout.cardveiw_item_book,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {

            holder.tv_book_title.setText(mData.get(position).getTitle());


            holder.cardView.setOnClickListener(v -> {

                tvbuku.setText(mData.get(position).getTitle());
                tvbuku.setTextColor(Color.rgb(00,00,00));
                editText.setText("tekan icon mic");

            });



        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv_book_title;
            CardView cardView ;

            TextView tvbuku;

            public MyViewHolder(View itemView) {
                super(itemView);

                tv_book_title = itemView.findViewById(R.id.huruf_title_id) ;
                cardView = itemView.findViewById(R.id.cardview_huruf_id);
                tvbuku = itemView.findViewById(R.id.namabuku);


            }
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        speechRecognizer.destroy();
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},RecordAudioRequestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RecordAudioRequestCode && grantResults.length > 0 ){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
        }
    }

    private class Kata {
        private String Title;


        public Kata() {
        }

        public Kata(String title) {
            Title = title;

        }


        public String getTitle() {
            return Title;
        }



        public void setTitle(String title) {
            Title = title;
        }


    }
}
