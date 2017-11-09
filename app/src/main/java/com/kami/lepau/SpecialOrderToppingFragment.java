package com.kami.lepau;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kami.lepau.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialOrderToppingFragment extends Fragment {

    String riceType;
    String size;
    int spiceLevel;

    View view;


    public SpecialOrderToppingFragment() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            riceType = bundle.getString("riceType");
            size = bundle.getString("size");
            spiceLevel = bundle.getInt("spiceLevel");
        }

        if (riceType == null || size == null) {
            Intent intent = new Intent(this.getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_special_order_topping, container, false);
    }

}
