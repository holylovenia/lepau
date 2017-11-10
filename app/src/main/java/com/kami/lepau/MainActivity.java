package com.kami.lepau;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.kami.lepau.R;

public class MainActivity extends AppCompatActivity {

    private boolean backPressedOnce = false;
    private Handler handler = new Handler();

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            backPressedOnce = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DEBUG: Auto Redirect to Special OrderItem
        Intent intent = new Intent(this, SpecialOrderActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDestroy() {
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (backPressedOnce) {
            super.onBackPressed();
            return;
        }
        backPressedOnce = true;
        Toast.makeText(this, "Press back again to leave", Toast.LENGTH_SHORT).show();

        handler.postDelayed(runnable, 2000);
    }
}
