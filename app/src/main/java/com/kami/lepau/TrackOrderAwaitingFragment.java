package com.kami.lepau;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import static android.content.Context.NOTIFICATION_SERVICE;

public class TrackOrderAwaitingFragment extends Fragment {

    private NotificationReceiver notifyReceiver;
    private NotificationManager notifyManager;

    static final int NOTIFICATION_ID = 0;
    private static final String ACTION_UPDATE_NOTIFICATION = "com.kami.lepau.ACTION_UPDATE_NOTIFICATION";
    private static final String ACTION_CANCEL_NOTIFICATION = "com.kami.lepau.ACTION_CANCEL_NOTIFICATION";

    private FragmentManager fragmentManager;

    private View view;
    private ImageView ivIcon;
    private Button btnCancel;

    private Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent notificationIntent = new Intent(getActivity(), MenuActivity.class);
            PendingIntent notificationPendingIntent = PendingIntent.getActivity(getContext(), NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            String message = ("Your order has been confirmed by our manager and is currently being prepared");
            NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(getContext())
                    .setContentTitle("Order Confirmed")
                    .setContentText(message)
                    .setContentIntent(notificationPendingIntent)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setSmallIcon(R.drawable.lepau_plate_transparent);

            notifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());

            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.right_enter, R.anim.left_exit)
                    .replace(R.id.toFrameContainer, new TrackOrderCookingFragment(), "TrackOrderCookingFragment")
                    .commit();
        }
    };

    public TrackOrderAwaitingFragment() {
        handler.postDelayed(runnable, 5000);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_track_order_awaiting, container, false);
        fragmentManager = getActivity().getSupportFragmentManager();

        ivIcon = (ImageView) view.findViewById(R.id.to_awaiting_icon);

        notifyManager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
        notifyReceiver = new NotificationReceiver();
        getActivity().registerReceiver(notifyReceiver, new IntentFilter(ACTION_UPDATE_NOTIFICATION));

        btnCancel = (Button) view.findViewById(R.id.to_awaiting_cancel_button);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MenuActivity.class);
                startActivity(intent);
            }
        });


        ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent notificationIntent = new Intent(getActivity(), MenuActivity.class);
                PendingIntent notificationPendingIntent = PendingIntent.getActivity(getContext(), NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                String message = ("Your order has been confirmed by our manager and is currently being prepared");
                NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(getContext())
                        .setContentTitle("Order Confirmed")
                        .setContentText(message)
                        .setContentIntent(notificationPendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setSmallIcon(R.drawable.lepau_plate_transparent);

                notifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());

                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_exit)
                        .replace(R.id.toFrameContainer, new TrackOrderCookingFragment(), "TrackOrderCookingFragment")
                        .commit();
            }
        });

        return view;
    }

    public class NotificationReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case ACTION_CANCEL_NOTIFICATION:
                    break;
                case ACTION_UPDATE_NOTIFICATION:
                    break;
            }
        }
    }
}
