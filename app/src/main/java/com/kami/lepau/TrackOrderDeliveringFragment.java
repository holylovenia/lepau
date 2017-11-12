package com.kami.lepau;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Locale;

import static android.content.Context.NOTIFICATION_SERVICE;

public class TrackOrderDeliveringFragment extends Fragment {

    private NotificationReceiver notifyReceiver;
    private NotificationManager notifyManager;

    static final int NOTIFICATION_ID = 0;
    private static final String ACTION_UPDATE_NOTIFICATION = "com.kami.lepau.ACTION_UPDATE_NOTIFICATION";
    private static final String ACTION_CANCEL_NOTIFICATION = "com.kami.lepau.ACTION_CANCEL_NOTIFICATION";

    private FragmentManager fragmentManager;

    private View view;
    private ImageView ivIcon;

    private Button btnConfirm;

    public TrackOrderDeliveringFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_track_order_delivering, container, false);
        fragmentManager = getActivity().getSupportFragmentManager();

        ivIcon = (ImageView) view.findViewById(R.id.to_delivering_icon);

        notifyManager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
        notifyReceiver = new NotificationReceiver();
        getActivity().registerReceiver(notifyReceiver, new IntentFilter(ACTION_UPDATE_NOTIFICATION));

        ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent notificationIntent = new Intent(getActivity(), MenuActivity.class);
                PendingIntent notificationPendingIntent = PendingIntent.getActivity(getContext(), NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                String message = ("Delivery confirmed by manager, thank you for using Lepau");
                NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(getContext())
                        .setContentTitle("Enjoy Your Meal!")
                        .setContentText(message)
                        .setContentIntent(notificationPendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setSmallIcon(R.drawable.lepau_plate_transparent);

                notifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());

                Intent intent = new Intent(getActivity(), MenuActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnConfirm = (Button) view.findViewById(R.id.to_delivering_confirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Have you received your order?");
                builder.setCancelable(true);

                builder.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Intent notificationIntent = new Intent(getActivity(), MenuActivity.class);
                                PendingIntent notificationPendingIntent = PendingIntent.getActivity(getContext(), NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                                String message = ("Thank you for using Lepau");
                                NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(getContext())
                                        .setContentTitle("Enjoy Your Meal!")
                                        .setContentText(message)
                                        .setContentIntent(notificationPendingIntent)
                                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                                        .setSmallIcon(R.drawable.lepau_plate_transparent);

                                notifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());

                                Intent intent = new Intent(getActivity(), MenuActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                            }
                        });

                builder.setNegativeButton(
                        "Not yet",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
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
