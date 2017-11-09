package com.kami.lepau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kami.lepau.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DEBUG: Auto Redirect to Special Order
        Intent intent = new Intent(this, SpecialOrderActivity.class);
        startActivity(intent);
        finish();
    }
}
