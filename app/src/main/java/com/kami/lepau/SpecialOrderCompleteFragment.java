package com.kami.lepau;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kami.lepau.data.OrderItem;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialOrderCompleteFragment extends Fragment {

    private String riceType;
    private String size;
    private int spiceLevel;
    private int toppingAmount;
    private int pricePerItem;
    private int quantity;

    private OrderItem orderItem;

    private FragmentManager fragmentManager;

    private View view;
    private TextView tvRiceType;
    private TextView tvRicePrice;
    private TextView tvSize;
    private TextView tvSizePrice;
    private TextView tvSpicinessLevel;
    private TextView tvToppingAmount;
    private TextView tvToppingPrice;
    private TextView tvItemPrice;
    private TextView tvItemQuantity;
    private TextView tvTotalPrice;

    private SeekBar sbStatus;

    private Button btnInc;
    private Button btnDec;
    private Button btnFinish;

    public SpecialOrderCompleteFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_special_order_complete, container, false);
        fragmentManager = getActivity().getSupportFragmentManager();


        pricePerItem = 0;
        if (savedInstanceState == null) {
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                riceType = bundle.getString("riceType");
                size = bundle.getString("size");
                spiceLevel = bundle.getInt("spiceLevel");
                toppingAmount = bundle.getInt("toppingAmount");
            }
            orderItem = new OrderItem(true, getString(R.string.custom_fried_rice), getString(R.string.lepau_specialty));
        }

        tvRiceType = (TextView) view.findViewById(R.id.so_complete_rice);
        tvRicePrice = (TextView) view.findViewById(R.id.so_complete_rice_price);
        tvSize = (TextView) view.findViewById(R.id.so_complete_size);
        tvSizePrice = (TextView) view.findViewById(R.id.so_complete_size_price);
        tvSpicinessLevel = (TextView) view.findViewById(R.id.so_complete_spiciness);
        tvToppingAmount = (TextView) view.findViewById(R.id.so_complete_topping);
        tvToppingPrice = (TextView) view.findViewById(R.id.so_complete_topping_price);
        tvItemPrice = (TextView) view.findViewById(R.id.so_complete_item_price);
        tvItemQuantity = (TextView) view.findViewById(R.id.so_complete_quantity);
        tvTotalPrice = (TextView) view.findViewById(R.id.so_complete_total_price);

        btnInc = (Button) view.findViewById(R.id.so_complete_inc_quantity);
        btnDec = (Button) view.findViewById(R.id.so_complete_dec_quantity);
        btnFinish = (Button) view.findViewById(R.id.so_complete_finish);

        // Show order data
        // Rice Type
        switch (riceType) {
            case "White rice": {
                tvRiceType.setText(R.string.so_ricetype_white);
                int price = 10000;
                pricePerItem += price;
                String priceStr = getString(R.string.rp) + String.valueOf(price);
                tvRicePrice.setText(priceStr);
                break;
            }
            case "Jasmine rice": {
                tvRiceType.setText(R.string.so_ricetype_jasmine);
                int price = 12000;
                pricePerItem += price;
                String priceStr = getString(R.string.rp) + String.valueOf(price);
                tvRicePrice.setText(priceStr);
                break;
            }
            case "Brown rice": {
                tvRiceType.setText(R.string.so_ricetype_brown);
                int price = 14000;
                pricePerItem += price;
                String priceStr = getString(R.string.rp) + String.valueOf(price);
                tvRicePrice.setText(priceStr);
                break;
            }
            default: {
                tvRiceType.setText(R.string.so_ricetype_sticky);
                int price = 14000;
                pricePerItem += price;
                String priceStr = getString(R.string.rp) + String.valueOf(price);
                tvRicePrice.setText(priceStr);
                break;
            }
        }

        // Size
        if (size.equals(getString(R.string.so_size_small))) {
            tvSize.setText(R.string.so_size_small);
            int price = 0;
            pricePerItem += price;
            String priceStr = getString(R.string.rp) + String.valueOf(price);
            tvSizePrice.setText(priceStr);
        }
        else if (size.equals(getString(R.string.so_size_medium))) {
            tvSize.setText(R.string.so_size_medium);
            int price = 3000;
            pricePerItem += price;
            String priceStr = getString(R.string.rp) + String.valueOf(price);
            tvSizePrice.setText(priceStr);
        } else {
            tvSize.setText(R.string.so_size_large);
            int price = 5000;
            pricePerItem += price;
            String priceStr = getString(R.string.rp) + String.valueOf(price);
            tvSizePrice.setText(priceStr);
        }

        // Spiciness
        if (spiceLevel == 0) {
            tvSpicinessLevel.setText(R.string.so_spice_0);
        }
        else if (spiceLevel == 1) {
            tvSpicinessLevel.setText(R.string.so_spice_1);
        }
        else if (spiceLevel == 2) {
            tvSpicinessLevel.setText(R.string.so_spice_2);
        }
        else if (spiceLevel == 3) {
            tvSpicinessLevel.setText(R.string.so_spice_3);
        }
        else if (spiceLevel == 4) {
            tvSpicinessLevel.setText(R.string.so_spice_4);
        }
        else if (spiceLevel == 5) {
            tvSpicinessLevel.setText(R.string.so_spice_5);
        }

        // Topping
        if (toppingAmount == 0) {
            tvToppingAmount.setText(R.string.so_topping_0);
        } else if (toppingAmount == 1) {
            tvToppingAmount.setText(R.string.so_topping_1);
        } else if (toppingAmount == 2) {
            tvToppingAmount.setText(R.string.so_topping_2);
        } else if (toppingAmount == 3) {
            tvToppingAmount.setText(R.string.so_topping_3);
        } else if (toppingAmount == 4) {
            tvToppingAmount.setText(R.string.so_topping_4);
        } else if (toppingAmount == 5) {
            tvToppingAmount.setText(R.string.so_topping_5);
        } else if (toppingAmount == 6) {
            tvToppingAmount.setText(R.string.so_topping_6);
        } else if (toppingAmount == 7) {
            tvToppingAmount.setText(R.string.so_topping_7);
        } else if (toppingAmount == 8) {
            tvToppingAmount.setText(R.string.so_topping_8);
        } else {
            tvToppingAmount.setText(R.string.so_topping_9);
        }

        int price = 3000 * toppingAmount;
        pricePerItem += price;
        String priceStr = getString(R.string.rp) + String.valueOf(price);
        tvToppingPrice.setText(priceStr);

        String itemPriceStr = getString(R.string.rp) + String.valueOf(pricePerItem);
        tvItemPrice.setText(itemPriceStr);

        quantity = Integer.parseInt(tvItemQuantity.getText().toString());
        String totalPriceStr = getString(R.string.rp) + String.valueOf(pricePerItem * quantity);
        tvTotalPrice.setText(totalPriceStr);

        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                String quantityStr = String.valueOf(quantity);
                tvItemQuantity.setText(quantityStr);
                String totalPriceStr = getString(R.string.rp) + String.valueOf(quantity * pricePerItem);
                tvTotalPrice.setText(totalPriceStr);
            }
        });

        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity <= 0) {
                    quantity = 0;
                    Toast.makeText(getActivity(), R.string.quantity_cant_be_negative, Toast.LENGTH_SHORT).show();
                }
                else {
                    quantity--;
                }
                String quantityStr = String.valueOf(quantity);
                tvItemQuantity.setText(quantityStr);

                String totalPriceStr = getString(R.string.rp) + String.valueOf(quantity * pricePerItem);
                tvTotalPrice.setText(totalPriceStr);
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderItem.setQuantity(quantity);
                orderItem.setPricePerItem(pricePerItem);
                orderItem.getSpecialOrderItem().setRiceType(riceType);
                orderItem.getSpecialOrderItem().setSize(size);
                orderItem.getSpecialOrderItem().setSpicinessLevel(spiceLevel);
                int[] mOrderItemQuantities = getActivity().getIntent().getIntArrayExtra("mOrderItemQuantities");
                Intent intent = new Intent(getActivity(), MenuActivity.class);
                intent.putExtra("SpecialOrder", orderItem);
                if(mOrderItemQuantities != null) {
                    intent.putExtra("OrderItemQuantities", mOrderItemQuantities);
                }
                startActivity(intent);
                getActivity().finish();
            }
        });

        sbStatus = (SeekBar) view.findViewById(R.id.so_complete_status_seekbar);

        sbStatus.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        return view;
    }

}
