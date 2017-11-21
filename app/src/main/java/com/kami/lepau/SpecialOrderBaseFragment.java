package com.kami.lepau;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialOrderBaseFragment extends Fragment {

    private FragmentManager fragmentManager;

    private View view;
    private Spinner spRiceType;
    private RadioGroup rgSize;
    private RadioButton rbSmall;
    private RadioButton rbMedium;
    private RadioButton rbLarge;
    private SeekBar sbSpiciness;
    private TextView tvSpiciness;
    private Button btnNext;

    private SeekBar sbStatus;

    public SpecialOrderBaseFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_special_order_base, container, false);
        fragmentManager = getActivity().getSupportFragmentManager();

        spRiceType = (Spinner) view.findViewById(R.id.so_base_spinner);
        rgSize = (RadioGroup) view.findViewById(R.id.so_base_radio);
        rbSmall = (RadioButton) view.findViewById(R.id.so_base_radio_small);
        rbMedium = (RadioButton) view.findViewById(R.id.so_base_radio_medium);
        rbLarge = (RadioButton) view.findViewById(R.id.so_base_radio_large);
        sbSpiciness = (SeekBar) view.findViewById(R.id.so_base_seekbar);
        tvSpiciness = (TextView) view.findViewById(R.id.so_base_spiciness_info);
        btnNext = (Button) view.findViewById(R.id.so_base_next);

        sbStatus = (SeekBar) view.findViewById(R.id.so_base_status_seekbar);

        sbStatus.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        rgSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.so_base_radio_small) {
                    rbSmall.setText(R.string.so_small);
                    rbMedium.setText(R.string.so_medium);
                    rbLarge.setText(R.string.so_large);
                }
                else if (i == R.id.so_base_radio_medium) {
                    rbSmall.setText(R.string.so_small_alt_1);
                    rbMedium.setText(R.string.so_medium_alt_1);
                    rbLarge.setText(R.string.so_large_alt_1);
                }
                else {
                    rbSmall.setText(R.string.so_small_alt_2);
                    rbMedium.setText(R.string.so_medium_alt_2);
                    rbLarge.setText(R.string.so_large_alt_2);
                }
            }
        });

        sbSpiciness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i == 0) {
                    tvSpiciness.setText(R.string.so_spice_0);
                }
                else if (i <=20) {
                    tvSpiciness.setText(R.string.so_spice_1);
                }
                else if (i <= 40) {
                    tvSpiciness.setText(R.string.so_spice_2);
                }
                else if (i <= 60) {
                    tvSpiciness.setText(R.string.so_spice_3);
                }
                else if (i <= 80) {
                    tvSpiciness.setText(R.string.so_spice_4);
                }
                else {
                    tvSpiciness.setText(R.string.so_spice_5);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String riceType = spRiceType.getSelectedItem().toString();
                riceType = riceType.substring(0, riceType.length()-5);

                int radioButtonID = rgSize.getCheckedRadioButtonId();
                View radioButton = rgSize.findViewById(radioButtonID);
                int index = rgSize.indexOfChild(radioButton);

                String size;
                if (index == 0) {
                    size = getString(R.string.so_small);
                }
                else if (index == 1) {
                    size = getString(R.string.so_medium);
                }
                else {
                    size = getString(R.string.so_large);
                }

                int spiceLevel = sbSpiciness.getProgress()/20;

                Bundle bundle = new Bundle();
                bundle.putString("riceType", riceType);
                bundle.putString("size", size);
                bundle.putInt("spiceLevel", spiceLevel);

                Fragment toppingFrag = new SpecialOrderToppingFragment();
                toppingFrag.setArguments(bundle);

                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_exit)
                        .replace(R.id.soFrameContainer, toppingFrag, "SpecialOrderToppingFragment")
                        .commit();
            }
        });

        return view;
    }

}
