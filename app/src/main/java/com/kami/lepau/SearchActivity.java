package com.kami.lepau;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        String searchQuery = "Searching for " + getIntent().getStringExtra("query");
        tvInfo = (TextView) findViewById(R.id.sa_search_text);
        tvInfo.setText(searchQuery);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SearchActivity.this, MenuActivity.class);
                SearchActivity.this.startActivity(mainIntent);
                SearchActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
