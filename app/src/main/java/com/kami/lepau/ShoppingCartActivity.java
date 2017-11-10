package com.kami.lepau;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kami.lepau.data.Order;
import com.kami.lepau.data.ShoppingCartAdapter;

public class ShoppingCartActivity extends AppCompatActivity {
    //Member variables
    private RecyclerView mRecyclerView;
    private Order mOrder;
    private ShoppingCartAdapter mAdapter;
    private Button cancelButton;
    private Button confirmButton;
    private TextView totalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        mOrder = (Order) getIntent().getSerializableExtra("Order");

        //Initialize the RecyclerView
        mRecyclerView = (RecyclerView)findViewById(R.id.activityShoppingCart_orderRecyclerView);

        //Set the Layout Manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Initialize the adapter and set it ot the RecyclerView
        mAdapter = new ShoppingCartAdapter(mOrder.getAllOrders(), this);
        mRecyclerView.setAdapter(mAdapter);

        totalTextView = (TextView) findViewById(R.id.activityShoppingCart_totalTextView);
        totalTextView.setText(Integer.toString(mOrder.getTotal()));

        cancelButton = (Button) findViewById(R.id.activityShoppingCart_cancelOrderButton);
        confirmButton = (Button) findViewById(R.id.activityShoppingCart_confirmOrderButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
