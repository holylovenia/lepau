package com.kami.lepau.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kami.lepau.R;

import java.util.ArrayList;

/**
 * Created by Holy on 09-Nov-17.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {
    private ArrayList<OrderItem> orderItems;
    private Context context;

    public ShoppingCartAdapter(ArrayList<OrderItem> orderItems, Context context) {
        this.orderItems = orderItems;
        int i = 0;
        while(i < orderItems.size()) {
            if(orderItems.get(i).getQuantity() == 0) {
                orderItems.remove(i);
            } else {
                i++;
            }
        }
        this.context = context;
    }

    @Override
    public ShoppingCartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.shopping_cart_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ShoppingCartAdapter.ViewHolder holder, int position) {
        OrderItem currentOrderItem = orderItems.get(position);
        holder.bindTo(currentOrderItem);
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mPriceText;
        private TextView mQuantityText;
        private TextView mSubtotalText;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         * @param itemView The rootview of the list_item.xml layout file
         */
        ViewHolder(View itemView) {
            super(itemView);

            //Initialize the views
            mTitleText = (TextView)itemView.findViewById(R.id.shoppingCartList_menuTitleTextView);
            mQuantityText = (TextView) itemView.findViewById(R.id.shoppingCartList_menuQuantityTextView);
            mPriceText = (TextView) itemView.findViewById(R.id.shoppingCartList_menuPriceTextView);
            mSubtotalText = (TextView) itemView.findViewById(R.id.shoppingCartList_menuSubtotalTextView);

            itemView.setOnClickListener(this);
        }

        void bindTo(OrderItem currentOrderItem){
            //Populate the textviews with data
            Log.d("BIND TO", currentOrderItem.getItemName());
            mTitleText.setText(currentOrderItem.getItemName());
            mQuantityText.setText(String.valueOf(currentOrderItem.getQuantity()));
            mPriceText.setText(String.valueOf(currentOrderItem.getPricePerItem()));
            mSubtotalText.setText(String.valueOf(currentOrderItem.getTotalPrice()));
        }

        @Override
        public void onClick(View v) {

        }
    }
}
