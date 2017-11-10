package com.kami.lepau;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    private EditText etItemQuantity;
    private TextView tvTotalPrice;

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
            orderItem = new OrderItem(true);
        }

        tvRiceType = (TextView) view.findViewById(R.id.so_complete_rice);
        tvRicePrice = (TextView) view.findViewById(R.id.so_complete_rice_price);
        tvSize = (TextView) view.findViewById(R.id.so_complete_size);
        tvSizePrice = (TextView) view.findViewById(R.id.so_complete_size_price);
        tvSpicinessLevel = (TextView) view.findViewById(R.id.so_complete_spiciness);
        tvToppingAmount = (TextView) view.findViewById(R.id.so_complete_topping);
        tvToppingPrice = (TextView) view.findViewById(R.id.so_complete_topping_price);
        tvItemPrice = (TextView) view.findViewById(R.id.so_complete_item_price);
        etItemQuantity = (EditText) view.findViewById(R.id.so_complete_quantity);
        tvTotalPrice = (TextView) view.findViewById(R.id.so_complete_total_price);

        btnInc = (Button) view.findViewById(R.id.so_complete_inc_quantity);
        btnDec = (Button) view.findViewById(R.id.so_complete_dec_quantity);
        btnFinish = (Button) view.findViewById(R.id.so_complete_finish);

        // Show order data
        // Rice Type
        if (riceType.equals("White rice")) {
            tvRiceType.setText(R.string.so_ricetype_white);
            int price = 10000;
            pricePerItem += price;
            String priceStr = "Rp" + String.valueOf(price);
            tvRicePrice.setText(priceStr);
        }
        else if (riceType.equals("Jasmine rice")) {
            tvRiceType.setText(R.string.so_ricetype_jasmine);
            int price = 12000;
            pricePerItem += price;
            String priceStr = "Rp" + String.valueOf(price);
            tvRicePrice.setText(priceStr);
        }
        else if (riceType.equals("Brown rice")) {
            tvRiceType.setText(R.string.so_ricetype_brown);
            int price = 14000;
            pricePerItem += price;
            String priceStr = "Rp" + String.valueOf(price);
            tvRicePrice.setText(priceStr);
        }
        else {
            tvRiceType.setText(R.string.so_ricetype_sticky);
            int price = 14000;
            pricePerItem += price;
            String priceStr = "Rp" + String.valueOf(price);
            tvRicePrice.setText(priceStr);
        }

        // Size
        if (size.equals("Small")) {
            tvSize.setText(R.string.so_size_small);
            int price = 0;
            pricePerItem += price;
            String priceStr = "Rp" + String.valueOf(price);
            tvSizePrice.setText(priceStr);
        }
        else if (size.equals("Medium")) {
            tvSize.setText(R.string.so_size_medium);
            int price = 3000;
            pricePerItem += price;
            String priceStr = "Rp" + String.valueOf(price);
            tvSizePrice.setText(priceStr);
        } else {
            tvSize.setText(R.string.so_size_large);
            int price = 5000;
            pricePerItem += price;
            String priceStr = "Rp" + String.valueOf(price);
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
        String priceStr = "Rp" + String.valueOf(price);
        tvToppingPrice.setText(priceStr);

        String itemPriceStr = "Rp" + String.valueOf(pricePerItem);
        tvItemPrice.setText(itemPriceStr);

        quantity = Integer.parseInt(etItemQuantity.getText().toString());
        String totalPriceStr = "Rp" + String.valueOf(pricePerItem * quantity);
        tvTotalPrice.setText(totalPriceStr);
        orderItem.setPricePerItem(pricePerItem);
        etItemQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                quantity = Integer.valueOf(charSequence.toString());
                String priceStr = "Rp" + String.valueOf(pricePerItem * quantity);
                tvTotalPrice.setText(priceStr);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                String quantityStr = String.valueOf(quantity);
                etItemQuantity.setText(quantityStr);
                orderItem.setQuantity(quantity);
            }
        });

        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity <= 0) {
                    quantity = 0;
                    Toast.makeText(getActivity(), "Quantity can't be negative", Toast.LENGTH_SHORT).show();
                }
                else {
                    quantity--;
                }
                String quantityStr = String.valueOf(quantity);
                etItemQuantity.setText(quantityStr);
                orderItem.setQuantity(quantity);
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MenuActivity.class);
                intent.putExtra("SpecialOrder", orderItem);
                startActivity(intent);
                getActivity().finish();
            }
        });


        return view;
    }

}
