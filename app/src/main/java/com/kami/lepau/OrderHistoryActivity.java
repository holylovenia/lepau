package com.kami.lepau;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.kami.lepau.data.Order;
import com.kami.lepau.data.OrderHistoryAdapter;
import com.kami.lepau.data.OrderItem;

import java.util.ArrayList;
import java.util.Calendar;

public class OrderHistoryActivity extends AppCompatActivity {
    private ArrayList<Order> mOrders;
    private RecyclerView mRecyclerView;
    private OrderHistoryAdapter mAdapter;

    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        initializeOrders();

        //Initialize the RecyclerView
        mRecyclerView = (RecyclerView)findViewById(R.id.activityOrderHistory_orderHistoryRecyclerView);

        //Set the Layout Manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Initialize the adapter and set it ot the RecyclerView
        mAdapter = new OrderHistoryAdapter(mOrders, this);
        mRecyclerView.setAdapter(mAdapter);

        btnBack = (Button) findViewById(R.id.history_back_to_menu);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initializeOrders() {
        Order dummyOrder = new Order();
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -5);
        dummyOrder.setDate(cal.getTime());
        dummyOrder.addOrder(new OrderItem(getString(R.string.bacon_eggs), "", 10000, 1, -999));
        dummyOrder.addOrder(new OrderItem(getString(R.string.ketupat), "", 7500, 1, -999));
        dummyOrder.addOrder(new OrderItem(getString(R.string.omelette), "", 20000, 2, -999));

        Order dummyOrder2 = new Order();
        cal.add(Calendar.DATE, -2);
        dummyOrder2.setDate(cal.getTime());
        dummyOrder2.addOrder(new OrderItem(getString(R.string.indomie), "", 5000, 1, -999));
        dummyOrder2.addOrder(new OrderItem(getString(R.string.french_toast), "", 12500, 1, -999));

        Order dummyOrder3 = new Order();
        dummyOrder3.addOrder(new OrderItem(getString(R.string.custom_fried_rice), "", 30000, 2, -999));
        dummyOrder3.addOrder(new OrderItem(getString(R.string.ketupat), "", 7500, 2, -999));
        dummyOrder3.addOrder(new OrderItem(getString(R.string.indomie), "", 5000, 1, -999));

        mOrders = new ArrayList<>();
        mOrders.add(dummyOrder);
        mOrders.add(dummyOrder2);
        mOrders.add(dummyOrder3);
    }
}
