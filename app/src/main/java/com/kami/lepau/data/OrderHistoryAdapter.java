package com.kami.lepau.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kami.lepau.R;

import java.util.ArrayList;

/**
 * Created by Holy on 09-Nov-17.
 */

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder> {
    private ArrayList<Order> orders;
    private Context context;

    public OrderHistoryAdapter(ArrayList<Order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @Override
    public OrderHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.history_list, parent, false));
    }

    @Override
    public void onBindViewHolder(OrderHistoryAdapter.ViewHolder holder, int position) {
        //Get current sport
        Order currentOrder = orders.get(position);
        //Populate the textviews with data
        holder.bindTo(currentOrder);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Member Variables for the TextViews
        private TextView mDateText;
        private TextView mMenuListText;
        private TextView mPriceText;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         * @param itemView The rootview of the list_item.xml layout file
         */
        ViewHolder(View itemView) {
            super(itemView);

            //Initialize the views
            mDateText = (TextView)itemView.findViewById(R.id.historyList_dateTextView);
            mMenuListText = (TextView)itemView.findViewById(R.id.historyList_menuListTextView);
            mPriceText = (TextView) itemView.findViewById(R.id.historyList_historyPriceTextView);

            itemView.setOnClickListener(this);
        }

        void bindTo(Order currentOrder){
            //Populate the textviews with data
            mDateText.setText(currentOrder.getDate());
            mMenuListText.setText(currentOrder.getOrdersInString());
            mPriceText.setText(String.valueOf(currentOrder.getTotal()+2000));
        }

        @Override
        public void onClick(View v) {

        }
    }
}
