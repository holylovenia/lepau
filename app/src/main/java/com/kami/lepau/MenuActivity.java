package com.kami.lepau;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kami.lepau.data.OrderItem;
import com.kami.lepau.data.OrderItemsAdapter;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    //Member variables
    private RecyclerView mRecyclerView;
    private ArrayList<OrderItem> mOrderItems;
    private OrderItemsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

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
