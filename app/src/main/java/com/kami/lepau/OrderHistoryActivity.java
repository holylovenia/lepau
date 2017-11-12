package com.kami.lepau;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kami.lepau.data.Order;
import com.kami.lepau.data.OrderHistoryAdapter;
import com.kami.lepau.data.OrderItem;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {
    private ArrayList<Order> mOrders;
    private RecyclerView mRecyclerView;
    private OrderHistoryAdapter mAdapter;

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
    }

    private void initializeOrders() {
        Order dummyOrder = new Order();
        dummyOrder.addOrder(new OrderItem("Bacon and Eggs", "", 20000, 3, -999));
        dummyOrder.addOrder(new OrderItem("Ketupat", "", 15000, 5, -999));
        dummyOrder.addOrder(new OrderItem("Omelette", "", 40000, 2, -999));
        Order dummyOrder2 = new Order();
        dummyOrder2.addOrder(new OrderItem("Indomie", "", 10000, 4, -999));
        dummyOrder2.addOrder(new OrderItem("French Toast", "", 25000, 7, -999));
        mOrders = new ArrayList<>();
        mOrders.add(dummyOrder);
        mOrders.add(dummyOrder2);
    }
}
