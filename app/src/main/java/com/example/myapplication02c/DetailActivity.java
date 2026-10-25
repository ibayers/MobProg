package com.example.myapplication02c;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    // TAG = label untuk memfilter Logcat biar log kita tidak tenggelam
    String TAG = "CinePocket";

    TextView tvDetailTitle, tvDetailGenre;
    Button btnTrailer;
    View viewPosterDetail;
    String strTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.v(TAG, "detail: onCreate");

        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailGenre = findViewById(R.id.tvDetailGenre);
        btnTrailer = findViewById(R.id.btnTrailer);
        viewPosterDetail = findViewById(R.id.viewPosterDetail);

        // terima 3 extra: 2 String + 1 int (perhatikan tipenya beda-beda)
        strTitle = getIntent().getStringExtra("keyTitle");
        String strGenre = getIntent().getStringExtra("keyGenre");
        int posterColor = getIntent().getIntExtra("keyColor", 0xFF888888);

        tvDetailTitle.setText(strTitle);
        tvDetailGenre.setText(strGenre);
        viewPosterDetail.setBackgroundColor(posterColor);

        btnTrailer.setOnClickListener(v -> {
            // IMPLICIT INTENT: tidak sebut class tujuan, cuma sebut AKSI
            // sistem yang mencari app yang sanggup VIEW url ini (browser/YouTube)
            String url = "https://www.youtube.com/results?search_query="
                    + strTitle + " trailer";
            Intent niatBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(niatBrowser);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "detail: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "detail: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "detail: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "detail: onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(TAG, "detail: onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "detail: onDestroy");
    }
}
