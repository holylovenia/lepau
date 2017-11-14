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
        dummyOrder.addOrder(new OrderItem(getString(R.string.bacon_eggs), "", 20000, 3, -999));
        dummyOrder.addOrder(new OrderItem(getString(R.string.ketupat), "", 15000, 5, -999));
        dummyOrder.addOrder(new OrderItem(getString(R.string.omelette), "", 40000, 2, -999));
        Order dummyOrder2 = new Order();
        dummyOrder2.addOrder(new OrderItem(getString(R.string.indomie), "", 10000, 4, -999));
        dummyOrder2.addOrder(new OrderItem(getString(R.string.french_toast), "", 25000, 7, -999));
        mOrders = new ArrayList<>();
        mOrders.add(dummyOrder);
        mOrders.add(dummyOrder2);
    }
}
