package com.kami.lepau;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.kami.lepau.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialOrderToppingFragment extends Fragment {

    private String riceType;
    private String size;
    private int spiceLevel;

    private FragmentManager fragmentManager;

    private View view;
    private ArrayList<CheckBox> cbToppings;
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private CheckBox cb4;
    private CheckBox cb5;
    private CheckBox cb6;
    private CheckBox cb7;
    private CheckBox cb8;
    private CheckBox cb9;
    private Button btnNext;

    public SpecialOrderToppingFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_special_order_topping, container, false);
        fragmentManager = getActivity().getSupportFragmentManager();

        if (savedInstanceState == null) {
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                riceType = bundle.getString("riceType");
                size = bundle.getString("size");
                spiceLevel = bundle.getInt("spiceLevel");
            }
        }

        cbToppings = new ArrayList<>();
        cb1 = (CheckBox) view.findViewById(R.id.so_topping_scrambled_egg);
        cb2 = (CheckBox) view.findViewById(R.id.so_topping_sunny_side_up);
        cb3 = (CheckBox) view.findViewById(R.id.so_topping_chicken);
        cb4 = (CheckBox) view.findViewById(R.id.so_topping_beef);
        cb5 = (CheckBox) view.findViewById(R.id.so_topping_seafood);
        cb6 = (CheckBox) view.findViewById(R.id.so_topping_cheese);
        cb7 = (CheckBox) view.findViewById(R.id.so_topping_vegetable);
        cb8 = (CheckBox) view.findViewById(R.id.so_topping_sausage);
        cb9 = (CheckBox) view.findViewById(R.id.so_topping_meatball);

        cbToppings.add(cb1);
        cbToppings.add(cb2);
        cbToppings.add(cb3);
        cbToppings.add(cb4);
        cbToppings.add(cb5);
        cbToppings.add(cb6);
        cbToppings.add(cb7);
        cbToppings.add(cb8);
        cbToppings.add(cb9);

        btnNext = (Button) view.findViewById(R.id.so_topping_next);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int toppingAmount = 0;

                for (int i=0; i<cbToppings.size(); i++) {
                    if (cbToppings.get(i).isChecked()) {
                        toppingAmount++;
                    }
                }

                Bundle bundle = new Bundle();
                bundle.putString("riceType", riceType);
                bundle.putString("size", size);
                bundle.putInt("spiceLevel", spiceLevel);
                bundle.putInt("toppingAmount", toppingAmount);

                Fragment completeFrag = new SpecialOrderCompleteFragment();
                completeFrag.setArguments(bundle);

                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_exit)
                        .replace(R.id.frameContainer, completeFrag, "SpecialOrderCompleteFragment")
                        .commit();
            }
        });

        return view;
    }
}
