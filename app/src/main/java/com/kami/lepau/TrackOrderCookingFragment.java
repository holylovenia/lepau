package com.kami.lepau;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kami.lepau.R;

public class TrackOrderCookingFragment extends Fragment {

    private FragmentManager fragmentManager;

    private View view;
    private ImageView ivIcon;

    public TrackOrderCookingFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_track_order_cooking, container, false);
        fragmentManager = getActivity().getSupportFragmentManager();

        ivIcon = (ImageView) view.findViewById(R.id.to_cooking_icon);

        ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_exit)
                        .replace(R.id.toFrameContainer, new TrackOrderDeliveringFragment(), "TrackOrderCookingFragment")
                        .commit();
            }
        });

        return view;
    }

}
