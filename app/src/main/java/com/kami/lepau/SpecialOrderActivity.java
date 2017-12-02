package com.kami.lepau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import com.kami.lepau.data.OrderItem;

public class SpecialOrderActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private OrderItem orderItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_order);

        fragmentManager = this.getSupportFragmentManager();

        if (savedInstanceState == null) {
            orderItem = new OrderItem(true, getString(R.string.custom_fried_rice), getString(R.string.lepau_specialty));
            fragmentManager.beginTransaction()
                    .replace(R.id.soFrameContainer, new SpecialOrderBaseFragment(), "BaseFragment")
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
