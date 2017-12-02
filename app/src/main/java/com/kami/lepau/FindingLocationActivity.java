package com.kami.lepau;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class FindingLocationActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finding_location);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(FindingLocationActivity.this, MenuActivity.class);
                FindingLocationActivity.this.startActivity(mainIntent);
                FindingLocationActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
