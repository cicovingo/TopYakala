package com.sukruokul.topyakala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView zaman, puan;
    ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9;
    int score;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zaman = findViewById(R.id.zamanView);
        puan = findViewById(R.id.puanView);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        imageArray = new ImageView[]{imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView6,imageView7,imageView8,imageView9};

        runImages();

        score = 0;

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                zaman.setText("Zaman: "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                zaman.setText("Süre Bitti..");
                handler.removeCallbacks(runnable);
                hideImages();

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Tekrar Oyna!");
                alert.setMessage("Tekrar oynamak ister misiniz?");
                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Oyun Bitti..", Toast.LENGTH_LONG).show();
                    }
                });

                alert.show();
            }
        }.start();
    }

    public void increaseScore(View view){
        score++;
        puan.setText("Puan: "+score);
    }

    public void runImages(){

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                hideImages();
                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,500);
            }
        };

        handler.post(runnable);
    }

    public void hideImages(){
        for (ImageView image: imageArray){
            image.setVisibility(View.INVISIBLE);
        }
    }
}
