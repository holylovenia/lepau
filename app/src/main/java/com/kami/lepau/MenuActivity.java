package com.kami.lepau;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.kami.lepau.data.Order;
import com.kami.lepau.data.OrderItem;
import com.kami.lepau.data.OrderItemsAdapter;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    //Member variables
    private Order mOrder;
    private RecyclerView mRecyclerView;
    private ArrayList<OrderItem> mOrderItems;
    private OrderItemsAdapter mAdapter;
    private Button mOrderButton;
    private Button specialOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mOrder = new Order();

        //Initialize the RecyclerView
        mRecyclerView = (RecyclerView)findViewById(R.id.activityMenu_menuRecyclerView);

        //Set the Layout Manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Initialize the ArrayLIst that will contain the data
        mOrderItems = new ArrayList<>();

        //Initialize the adapter and set it ot the RecyclerView
        mAdapter = new OrderItemsAdapter(mOrderItems, this);
        mRecyclerView.setAdapter(mAdapter);

        //Get the data
        initializeData();

        specialOrderButton = (Button) findViewById(R.id.activityMenu_friedRiceButton);
        specialOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SpecialOrderActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mOrderButton = (Button) findViewById(R.id.activityMenu_orderButton);
        mOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<OrderItem> normalOrderItems = mAdapter.getOrderItems();
                OrderItem specialOrderItem = (OrderItem) getIntent().getSerializableExtra("SpecialOrder");
                if(specialOrderItem != null) {
                    mOrder.addOrder(specialOrderItem);
                }
                for(int i = 0; i < normalOrderItems.size(); i++) {
                    mOrder.addOrder(normalOrderItems.get(i));
                }
                Intent intent = new Intent(v.getContext(), ShoppingCartActivity.class);
                intent.putExtra("Order", mOrder);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * Method for initializing the sports data from resources.
     */
    private void initializeData() {
        //Get the resources from the XML file
        TypedArray orderItemImageResources = getResources().obtainTypedArray(R.array.menu_pictures);
        String[] orderItemTitles = getResources().getStringArray(R.array.menu_titles);
        String[] orderItemDescriptions = getResources().getStringArray(R.array.menu_descriptions);
        int[] orderItemPrices = getResources().getIntArray(R.array.menu_prices);

        //Clear the existing data (to avoid duplication)
        mOrderItems.clear();

        //Create the ArrayList of Sports objects with the titles and information about each sport
        for(int i=0; i<orderItemTitles.length; i++){
            mOrderItems.add(new OrderItem(orderItemTitles[i], orderItemDescriptions[i], orderItemPrices[i], 0, orderItemImageResources.getResourceId(i, 0)));
        }

        //Notify the adapter of the change
        mAdapter.notifyDataSetChanged();
        orderItemImageResources.recycle();
    }
}
