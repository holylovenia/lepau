package com.kami.lepau;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kami.lepau.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialOrderBaseFragment extends Fragment {


    public SpecialOrderBaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_special_order_base, container, false);
    }

}
