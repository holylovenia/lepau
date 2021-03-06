package com.kami.lepau;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kami.lepau.data.Order;
import com.kami.lepau.data.OrderItem;
import com.kami.lepau.data.OrderItemsAdapter;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final String SAVED_STATE = "savedOrder";
    //Member variables
    private Order mOrder;
    private RecyclerView mRecyclerView;
    private ArrayList<OrderItem> mOrderItems;
    private OrderItemsAdapter mAdapter;
    private Button mOrderButton;
    private Button specialOrderButton;
    private Parcelable recyclerViewState;
    private Toolbar mActionBarToolbar;

    private SearchView svMenu;

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
        setContentView(R.layout.activity_menu);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mOrder = new Order();

        //Initialize the RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.activityMenu_menuRecyclerView);

        //Set the Layout Manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mOrderItems = new ArrayList<>();
        mAdapter = new OrderItemsAdapter(mOrderItems, this);

        //Get the data
        if (savedInstanceState == null) {
            Log.d("SaveInstanceState", "NULL");
            mRecyclerView.setAdapter(mAdapter);
            initializeData();
        } else {
            Log.d("SaveInstanceState", "TIDAK NULL");
            Parcelable savedState = savedInstanceState.getParcelable(SAVED_STATE);
            mAdapter = new OrderItemsAdapter(mOrderItems, this);
            mRecyclerView.getLayoutManager().onRestoreInstanceState(savedState);
            mAdapter.notifyDataSetChanged();
        }

        mActionBarToolbar = (Toolbar) findViewById(R.id.menu_toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle(getString(R.string.pelesiran));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mActionBarToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        svMenu = (SearchView) findViewById(R.id.menu_search_bar);
        svMenu.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getBaseContext(), SearchActivity.class);
                intent.putExtra("query", svMenu.getQuery().toString());
                startActivity(intent);
                finish();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        specialOrderButton = (Button) findViewById(R.id.activityMenu_friedRiceButton);
        specialOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SpecialOrderActivity.class);
                int[] quantities = new int[mOrderItems.size()];
                for (int i = 0; i < mOrderItems.size(); i++) {
                    quantities[i] = mOrderItems.get(i).getQuantity();
                }
                intent.putExtra("mOrderItemQuantities", quantities);
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
                if (specialOrderItem != null) {
                    mOrder.addOrder(specialOrderItem);
                }
                for (int i = 0; i < normalOrderItems.size(); i++) {
                    if (normalOrderItems.get(i).getQuantity() != 0) {
                        mOrder.addOrder(normalOrderItems.get(i));
                    }
                }
                if (mOrder.getAllOrders().isEmpty()) {
                    Toast.makeText(getBaseContext(), R.string.your_order_is_empty, Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(v.getContext(), ShoppingCartActivity.class);
                    intent.putExtra("Order", mOrder);
                    startActivity(intent);
                    finish();
                }
            }
        });

        int position = getIntent().getIntExtra("searchResultPosition", -999);
        String query = getIntent().getStringExtra("searchQuery");

        if (position != -999) {
            if (position == -1) {
                String strErr = "Could not find " + query + " in our menus";
                Toast.makeText(this, strErr, Toast.LENGTH_LONG).show();
            } else {
                mRecyclerView.scrollToPosition(position);
            }
        }
    }

    /**
     * Method for initializing the food data from resources.
     */
    private void initializeData() {
        //Get the resources from the XML file
        TypedArray orderItemImageResources = getResources().obtainTypedArray(R.array.menu_pictures);
        String[] orderItemTitles = getResources().getStringArray(R.array.menu_titles);
        String[] orderItemDescriptions = getResources().getStringArray(R.array.menu_descriptions);
        int[] orderItemPrices = getResources().getIntArray(R.array.menu_prices);
        int[] quantities = getIntent().getIntArrayExtra("OrderItemQuantities");

        //Clear the existing data (to avoid duplication)
        mOrderItems.clear();

        //Create the ArrayList of food objects with the titles and information about each sport
        if (quantities == null) {
            for (int i = 0; i < orderItemTitles.length; i++) {
                mOrderItems.add(new OrderItem(orderItemTitles[i], orderItemDescriptions[i], orderItemPrices[i], 0, orderItemImageResources.getResourceId(i, 0)));
            }
        } else {
            for (int i = 0; i < orderItemTitles.length; i++) {
                mOrderItems.add(new OrderItem(orderItemTitles[i], orderItemDescriptions[i], orderItemPrices[i], quantities[i], orderItemImageResources.getResourceId(i, 0)));
            }
        }

        //Notify the adapter of the change
        mAdapter.notifyDataSetChanged();
        orderItemImageResources.recycle();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        recyclerViewState = mRecyclerView.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(SAVED_STATE, recyclerViewState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);

        // Retrieve list state and list/item positions
        if (state != null) {
            Log.d("OnRestoreInstanceState", "state != null");
            recyclerViewState = state.getParcelable(SAVED_STATE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (recyclerViewState != null) {
            Log.d("OnResume", "recyclerViewState != null");
            mRecyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
        }
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (backPressedOnce) {
                finishAffinity();
                return;
            }
            backPressedOnce = true;
            Toast.makeText(this, R.string.press_back_again_to_leave, Toast.LENGTH_SHORT).show();

            handler.postDelayed(runnable, 2000);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;

        switch(id) {
            case R.id.nav_menu:
                intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_history:
                intent = new Intent(this, OrderHistoryActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_sign_out:
                intent = new Intent(this, AuthenticationActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.dapur_dipatiukur:
                item.setChecked(true);
                getSupportActionBar().setTitle(getString(R.string.dipatiukur));
                break;
            case R.id.dapur_cisitu:
                item.setChecked(true);
                getSupportActionBar().setTitle(getString(R.string.cisitu));
                break;
            case R.id.dapur_pelesiran:
                item.setChecked(true);
                getSupportActionBar().setTitle(getString(R.string.pelesiran));
                break;
            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
