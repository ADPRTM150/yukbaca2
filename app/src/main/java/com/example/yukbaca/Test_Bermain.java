package com.example.yukbaca;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.yukbaca.fragment.Datascore;
import com.example.yukbaca.kata.Kata_Bermain_Membaca;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class Test_Bermain extends AppCompatActivity implements View.OnClickListener {
    private PTestSoal testSoal = new PTestSoal();
    private TextView tvQuestion, tvScore;
    private Button btn_a, btn_b,playsoal;
    private DatabaseHelper databaseHelper;
    private String mAnswer;
    Random acak1 = new Random();
    private ImageView ivQuestion;
    MediaPlayer benar,salah, soalmanakah, soalhuruf, soalmanagambar, soalmanakata;
    private int questionNumber = 0;
    private int score = 0,scorehuruf=0,scorebunyi=0,scorekata=0;
    private final String CREDENTIAL_SHARED_PREF = "our_shared_pref";
    TextView tvbuku;
    public static final Integer RecordAudioRequestCode = 1;
    private SpeechRecognizer speechRecognizer;
    private TextView editText;
    private ImageView micButton;
    String strUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kata__bermain_tebak);

        SharedPreferences credentials = getSharedPreferences(CREDENTIAL_SHARED_PREF, Context.MODE_PRIVATE);
        strUsername = credentials.getString("Username", null);

        databaseHelper = new DatabaseHelper(getBaseContext());

        ivQuestion =findViewById(R.id.iv_question);
        btn_a = findViewById(R.id.btn_answer_a);
        btn_b = findViewById(R.id.btn_answer_b);
        playsoal = findViewById(R.id.playsoal);
        playsoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soalhuruf.start();
            }
        });
        tvQuestion = findViewById(R.id.tv_question);
        tvScore = findViewById(R.id.tv_score);
        tvScore.setText("0");

        //suara
        benar = MediaPlayer.create(this, R.raw.yeeeea);
        salah = MediaPlayer.create(this, R.raw.salah);
        soalmanakah = MediaPlayer.create(this, R.raw.manakah_huruf);
        soalmanagambar = MediaPlayer.create(this, R.raw.gambar_dibawahini);
        soalmanakata = MediaPlayer.create(this, R.raw.manakah_kata);

        btn_a.setOnClickListener(this);
        btn_b.setOnClickListener(this);

        updateQuestion();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Soal "+questionNumber+" dari 20");
    }

    private void updateQuestion() {

        if (questionNumber < 5) {
//                int acaksoal1 = acak1.nextInt(13);
//                int randomNum = acak1.nextInt((5));
            ivQuestion.setImageResource(testSoal.getpQuestion(questionNumber));
            soalmanakah.start();
            soalhuruf = MediaPlayer.create(this, testSoal.getsQuestion(questionNumber));
            Handler handler = new Handler();
            handler.postDelayed(() -> soalhuruf.start(),2000);
            tvQuestion.setText(testSoal.getQuestion(questionNumber));
            btn_a.setText(testSoal.getChoice1(questionNumber));
            btn_b.setText(testSoal.getChoice2(questionNumber));
            mAnswer = testSoal.getCorrectAnswer(questionNumber);
            questionNumber++;
            getSupportActionBar().setTitle("Soal "+questionNumber+" dari 20");


        } else if (questionNumber < 10){
//            int randomNum = acak1.nextInt((10 - 5 + 1) + 5);
            ivQuestion.setImageResource(testSoal.getpQuestion(questionNumber));
            soalmanakah.start();
            soalhuruf = MediaPlayer.create(this, testSoal.getsQuestion(questionNumber));
            Handler handler = new Handler();
            handler.postDelayed(() -> soalhuruf.start(),2000);
            tvQuestion.setText(testSoal.getQuestion(questionNumber));
            btn_a.setText(testSoal.getChoice1(questionNumber));
            btn_b.setText(testSoal.getChoice2(questionNumber));
            mAnswer = testSoal.getCorrectAnswer(questionNumber);
            questionNumber++;
            getSupportActionBar().setTitle("Soal "+questionNumber+" dari 20");

        }else if (questionNumber < 15){
//            int randomNum = acak1.nextInt((15 - 10 + 1) + 10);
            ivQuestion.setImageResource(testSoal.getpQuestion(questionNumber));
            soalmanagambar.start();
            soalhuruf = MediaPlayer.create(this, testSoal.getsQuestion(questionNumber));
            Handler handler = new Handler();
            handler.postDelayed(() -> soalhuruf.start(),2000);
            tvQuestion.setText(testSoal.getQuestion(questionNumber));
            btn_a.setText(testSoal.getChoice1(questionNumber));
            btn_b.setText(testSoal.getChoice2(questionNumber));
            mAnswer = testSoal.getCorrectAnswer(questionNumber);
            questionNumber++;
            getSupportActionBar().setTitle("Soal "+questionNumber+" dari 20");

        }else if (questionNumber < 20){

            setContentView(R.layout.soal_membaca);
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
                checkPermission();
            }
            tvbuku = findViewById(R.id.soalbaca);
            micButton = findViewById(R.id.button);
            editText = findViewById(R.id.hasiltext);
            SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

            //bagian soal
            tvbuku.setText(testSoal.getQuestion(questionNumber));


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
                    String jawaban = data.get(0) ;
                    jawaban.toLowerCase();
                    editText.setText(jawaban);
                    String soal = (String) tvbuku.getText();

                    if (jawaban.equalsIgnoreCase(soal)){
                        tvbuku.setTextColor(Color.rgb(33,200,66));
                        Toast.makeText(Test_Bermain.this, "Benar"+jawaban +"="+soal,Toast.LENGTH_LONG).show();
                        scorebunyi = scorebunyi + 20;
                        scorekata = scorekata + 10;
                        score = score + 5;
                        updateScore(score);
                        Handler handler = new Handler();
                        handler.postDelayed(() ->updateQuestion(), 1000);
                        editText.setText("tekan icon mic kemudian baca kata");
                        questionNumber++;
                        getSupportActionBar().setTitle("Soal "+questionNumber+" dari 20");
                    }else {
                        Toast.makeText(Test_Bermain.this, "Salah "+jawaban +"!="+soal,Toast.LENGTH_LONG).show();
                        tvbuku.setTextColor(Color.rgb(230,0,0));
                        questionNumber++;
                        Handler handler = new Handler();
                        handler.postDelayed(() -> updateQuestion(),1000);
                        editText.setText("tekan icon mic kemudian baca kata");
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
                        micButton.setImageResource(R.drawable.ic_mic_black_on);
                        speechRecognizer.startListening(speechRecognizerIntent);
                    }
                    return false;
                }
            });


        }
        else {
            Datascore datascore = new Datascore(score,scorehuruf,scorebunyi,scorekata);
            //save hasil
            SQLiteDatabase create = databaseHelper.getWritableDatabase();
            //Membuat Map Baru, Yang Berisi Nama Kolom dan Data Yang Ingin Dimasukan
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.MyColumns.NAME, strUsername);
            values.put(DatabaseHelper.MyColumns.NILAI, datascore.getScore());
            values.put(DatabaseHelper.MyColumns.NILAI1, datascore.getScorehuruf());
            values.put(DatabaseHelper.MyColumns.NILAI2, datascore.getScorebunyi());
            values.put(DatabaseHelper.MyColumns.NILAI3, datascore.getScorekata());
            create.insert(DatabaseHelper.MyColumns.TABLE_NAME, null, values);
            //kirim hasil

            Intent i = new Intent(this, ResultTestActivity.class);
            i.putExtra("nama", strUsername);
            i.putExtra("score", datascore.getScore());
            i.putExtra("scorehuruf", datascore.getScorehuruf());
            i.putExtra("scorebunyi", datascore.getScorebunyi());
            i.putExtra("scorekata", datascore.getScorekata());
            startActivity(i);
            finish();
        }
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},RecordAudioRequestCode);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_answer_a:
                if (btn_a.getText() == mAnswer) {
                    if (questionNumber < 5) {
                        scorehuruf = scorehuruf + 20;
                    }else if(questionNumber < 10) {
                        scorebunyi = scorebunyi + 20;
                    }else {
                        scorekata = scorekata + 10;
                    }
                    score = score + 5;
                    updateScore(score);
                    updateQuestion();
                    Toast.makeText(this, "benar", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "salah", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
                break;
            case R.id.btn_answer_b:
                if (btn_b.getText() == mAnswer) {
                    if (questionNumber < 5) {
                        scorehuruf = scorehuruf + 20;
                    }else if(questionNumber < 10) {
                        scorebunyi = scorebunyi + 20;
                    }else {
                        scorekata = scorekata + 10;
                    }
                    score = score + 5;
                    updateScore(score);
                    updateQuestion();
                    Toast.makeText(this, "benar", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "salah", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
                break;

        }
    }

    @SuppressLint("SetTextI18n")
    private void updateScore(int score) {
        tvScore.setText("" + score);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Selesaikan Quiz", Toast.LENGTH_SHORT).show();
    }
}