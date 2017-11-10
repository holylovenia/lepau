package com.kami.lepau;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.kami.lepau.R;
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
            orderItem = new OrderItem(true);
            fragmentManager.beginTransaction()
                    .replace(R.id.frameContainer, new SpecialOrderBaseFragment(), "BaseFragment")
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
