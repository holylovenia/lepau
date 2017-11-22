package com.kami.lepau;


import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kami.lepau.R;

public class TrackOrderCookingFragment extends Fragment {

    private FragmentManager fragmentManager;

    private View view;
    private ImageView ivIcon;

    private Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.right_enter, R.anim.left_exit)
                    .replace(R.id.toFrameContainer, new TrackOrderDeliveringFragment(), "TrackOrderCookingFragment")
                    .commit();
        }
    };

    public TrackOrderCookingFragment() {
        handler.postDelayed(runnable, 5000);
    }

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
