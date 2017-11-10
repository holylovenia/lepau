package com.kami.lepau.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kami.lepau.R;

import java.util.ArrayList;

/**
 * Created by Holy on 09-Nov-17.
 */

public class OrderItemsAdapter extends RecyclerView.Adapter<OrderItemsAdapter.ViewHolder> {
    private ArrayList<OrderItem> orderItems;
    private Context context;

    public OrderItemsAdapter(ArrayList<OrderItem> orderItems, Context context) {
        this.orderItems = orderItems;
        this.context = context;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    @Override
    public OrderItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.menu_list, parent, false));
    }

    @Override
    public void onBindViewHolder(OrderItemsAdapter.ViewHolder holder, int position) {
        //Get current sport
        OrderItem currentOrderItem = orderItems.get(position);
        Glide.with(context).load(currentOrderItem.getImageResource()).into(holder.mOrderItemImage);
        //Populate the textviews with data
        holder.bindTo(currentOrderItem);
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mDescriptionText;
        private TextView mPriceText;
        private TextView mQuantityText;
        private ImageView mOrderItemImage;
        private Button mDecrementButton;
        private Button mIncrementButton;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         * @param itemView The rootview of the list_item.xml layout file
         */
        ViewHolder(View itemView) {
            super(itemView);

            //Initialize the views
            mOrderItemImage = (ImageView) itemView.findViewById(R.id.menuList_menuPicture);
            mTitleText = (TextView)itemView.findViewById(R.id.menuList_menuTitleTextView);
            mDescriptionText = (TextView)itemView.findViewById(R.id.menuList_menuDescriptionTextView);
            mQuantityText = (TextView) itemView.findViewById(R.id.menuList_menuQuantity);
            mPriceText = (TextView) itemView.findViewById(R.id.menuList_menuPriceTextView);
            mDecrementButton = (Button) itemView.findViewById(R.id.menuList_quantityDecrementButton);
            mIncrementButton = (Button) itemView.findViewById(R.id.menuList_quantityIncrementButton);

            mDecrementButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantity = Integer.parseInt(mQuantityText.getText().toString());
                    quantity--;
                    if(quantity < 0) {
                        quantity = 0;
                    }
                    orderItems.get(getAdapterPosition()).setQuantity(quantity);
                    mQuantityText.setText(Integer.toString(quantity));
                }
            });

            mIncrementButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantity = Integer.parseInt(mQuantityText.getText().toString());
                    quantity++;
                    orderItems.get(getAdapterPosition()).setQuantity(quantity);
                    mQuantityText.setText(Integer.toString(quantity));
                }
            });

            itemView.setOnClickListener(this);
        }

        void bindTo(OrderItem currentOrderItem){
            //Populate the textviews with data
            mTitleText.setText(currentOrderItem.getItemName());
            mDescriptionText.setText(currentOrderItem.getItemDescription());
            mQuantityText.setText(String.valueOf(currentOrderItem.getQuantity()));
            mPriceText.setText(String.valueOf(currentOrderItem.getPricePerItem()));
        }

        @Override
        public void onClick(View v) {

        }
    }
}
